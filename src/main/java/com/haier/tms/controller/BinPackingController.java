package com.haier.tms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haier.tms.common.Constant;
import com.haier.tms.request.BinPickingParam;
import com.haier.tms.response.BinPickingResult;
import com.haier.tms.service.AlgorithmService;
import com.haier.tms.util.AlgorithmCache;
import com.haier.tms.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Description: BinPackingController
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class BinPackingController {

    @Autowired
    private AlgorithmService algorithmService;

    @RequestMapping(value = "/binpicking", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BinPickingResult binPicking(HttpServletRequest request) {
        // get raw json from HttpServletRequest
        String requestJsonString = JsonUtil.getJSONParam(request);

        // check request json string
        if (!JsonUtil.checkRequest(JSON.parseObject(requestJsonString)))
            return BinPickingResult.builder()
                    .status("0").msg("Illegal import detected, Please verify the HTTP request!").build();

        // parse requestJsonString
        Map<String, String> inputs = JsonUtil.parseRequest(requestJsonString);

        // check cache first
        BinPickingResult value = AlgorithmCache.getValue(inputs.get(Constant.REQUEST));
        if (!value.getMsg().equals(Constant.NO_CACHE)) {
            log.info("Capture " + inputs.get(Constant.TRADE_Id) + " in the cache!");
            return JsonUtil.assembleTradeId(value, inputs.get(Constant.TRADE_Id));
        }

        // call the algorithm
        JSONObject responseJsonObject = null;
        BinPickingResult binPickingResult = null;
        try {

            // TODO: 调用方法

        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            log.warn("runtime exception !");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // store in cache if `Status` == 1
        if (binPickingResult.getStatus().equals("1")) {
            AlgorithmCache.setValue(inputs.get(Constant.REQUEST), binPickingResult);
            log.info("store in cache success!");
        }

        return JsonUtil.assembleTradeId(binPickingResult, inputs.get(Constant.TRADE_Id));
    }

}
