package com.haier.tms.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haier.tms.common.Constant;
import com.haier.tms.request.*;
import com.haier.tms.response.BinPickingResult;
import com.haier.tms.response.BinPickingResultTrain;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * Description: String to Json
 */
@Slf4j
public class JsonUtil {

    /**
     * check the request json object attribute.
     *
     * @param request com.alibaba.fastjson.JSONObject
     * @return Boolean
     */
    public static Boolean checkRequest(JSONObject request) {
        // tradeId check
        String tradeId = request.getString("tradeId");
        if (StringUtils.isEmpty(tradeId)) {
            log.warn("tradeId cannot be empty!");
            return false;
        }
        // orderList check
        JSONArray orderListObject = request.getJSONArray("orderList");
        String orderListJsonString = JSONObject.toJSONString(orderListObject);
        List<BinPickingOrder> orderList = JSONObject.parseArray(orderListJsonString, BinPickingOrder.class);
        if (CollectionUtils.isEmpty(orderList)) {
            log.warn("orderList cannot be empty!");
            return false;
        } else {
            for (BinPickingOrder order : orderList) {
                if (StringUtils.isEmpty(order.getOrderCode())) {
                    log.warn("orderCode cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(order.getUnloadingSequence())) {
                    log.warn("unloadingSequence cannot be empty!");
                    return false;
                }
                // Good List cannot be empty
                if (CollectionUtils.isEmpty(order.getGoodList())) {
                    log.warn("goodList cannot be empty!");
                    return false;
                } else { // get Good List
                    for (BinPickingGood binPickingGood : order.getGoodList()) {
                        if (ObjectUtils.isEmpty(binPickingGood)) {
                            log.warn("binPickingGood cannot be empty!");
                            return false;
                        }
                        List<BinPickingRestriction> restrictionList = binPickingGood.getRestrictionList();
                        if (CollectionUtils.isEmpty(restrictionList)) {
                            log.warn("restrictionList cannot be empty!");
                            return false;
                        }
                        // get restrictionList from binPickingGood
                        for (BinPickingRestriction binPickingRestriction : restrictionList) {
                            // check `isStack` field
                            // if `isStack` is false, then stack Level is 100
                            // means Goods are not allowed to stack
                            if (!binPickingRestriction.getIsStack()) {
                                binPickingRestriction.setStackLevel(100);
                            }
                        }
                    }
                }
            }
        }
        // vehicleModelList check
        JSONArray vehicleModelListObject = request.getJSONArray("vehicleModelList");
        String vehicleModelListJsonString = JSONObject.toJSONString(vehicleModelListObject);
        List<BinPickingVehicleModel> vehicleModelList = JSONObject.parseArray(vehicleModelListJsonString, BinPickingVehicleModel.class);
        if (CollectionUtils.isEmpty(vehicleModelList)) {
            log.warn("vehicleModelList cannot be empty!");
            return false;
        } else {
            for (BinPickingVehicleModel vehicleModel : vehicleModelList) {
                if (StringUtils.isEmpty(vehicleModel.getModelCode())) {
                    log.warn("modelCode cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(vehicleModel.getQty())) {
                    log.warn("qty cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(vehicleModel.getCubic())) {
                    log.warn("cubic cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(vehicleModel.getWeight())) {
                    log.warn("weight cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(vehicleModel.getLength())) {
                    log.warn("length cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(vehicleModel.getWidth())) {
                    log.warn("width cannot be empty!");
                    return false;
                }
                if (ObjectUtils.isEmpty(vehicleModel.getHeight())) {
                    log.warn("height cannot be empty!");
                    return false;
                }
            }
        }
        log.info("Pass parameter check");
        return true;
    }

    /**
     * Parse request to string.
     *
     * @param request HttpServletRequest
     * @return StringBuilder
     */
    public static String getJSONParam(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            String line = null;
            while ((line = streamReader.readLine()) != null) {
                stringBuilder.append(StringUtils.trimAllWhitespace(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // 从输入中 拿掉 id , string
    // 在输出时 装上 id , certain object

    /**
     * parse request json string and remove tradeId
     *
     * @param jsonString parseRequest
     * @return 1. tradeId 2. json string without tradeId
     */
    public static Map<String, String> parseRequest(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString); // toJSONObject

        String tradeId = (String) jsonObject.get(Constant.TRADE_Id); // get tradeId

        jsonObject.put(Constant.TRADE_Id, Constant.TRADE_Id); // remove tradeId
        String jsonStringWithoutTradeId = JSON.toJSONString(jsonObject); // toString

        HashMap<String, String> res = new HashMap<>();
        res.put(Constant.TRADE_Id, tradeId);
        res.put(Constant.REQUEST, jsonStringWithoutTradeId);
        return res;
    }

    /**
     * assemble tradeId for result
     *
     * @param binPickingResult result from algorithm
     * @param tradeId          tradeId from inputs
     * @return binPickingResult with tradeId
     */
    public static BinPickingResult assembleTradeId(BinPickingResult binPickingResult, String tradeId) {
        binPickingResult.setTradeId(tradeId);
        return binPickingResult;
    }

    /**
     * The assembly algorithm returns.
     *
     * @param jsonObject jsonObject
     * @return BinPickingResult instance
     */
    public static BinPickingResult assembleResult(JSONObject jsonObject) {
        String tradeId = (String) jsonObject.get("tradeId");
        if (StringUtils.isEmpty(tradeId)) log.warn("tradeId cannot be empty!");
        String status = (String) jsonObject.get("status");
        if (StringUtils.isEmpty(status)) status = "0";
        String msg = (String) jsonObject.get("msg");
        if (StringUtils.isEmpty(msg)) msg = "no message";

        Integer carNum = (Integer) jsonObject.get("carNum");
        if (ObjectUtils.isEmpty(carNum)) carNum = 0;
        Integer goodNum = (Integer) jsonObject.get("goodNum");
        if (ObjectUtils.isEmpty(goodNum)) goodNum = 0;

        // NOTE: java.lang.ClassCastException (BigDecimal)
        BigDecimal totalCapacityInt = (BigDecimal) jsonObject.get("totalCapacity");
        if (ObjectUtils.isEmpty(totalCapacityInt)) totalCapacityInt = new BigDecimal(0);
        BigDecimal totalWeightInt = (BigDecimal) jsonObject.get("totalWeight");
        if (ObjectUtils.isEmpty(totalWeightInt)) totalWeightInt = new BigDecimal(0);

        List<BinPickingResultTrain> trainList;
        trainList = (List<BinPickingResultTrain>) jsonObject.get("trainList");
        if (ObjectUtils.isEmpty(trainList)) trainList = new LinkedList<>();

        return BinPickingResult.builder()
                .tradeId(tradeId)
                .status(status).msg(msg)
                .carNum(carNum).goodNum(goodNum)
                .totalCapacity(totalCapacityInt.doubleValue())
                .totalWeight(totalWeightInt.doubleValue())
                .trainList(trainList)
                .build();
    }
}
