package com.haier.tms.request;

import lombok.Data;

import java.util.List;

/**
 * Description: 接收json输入的对象
 * Created By xxm
 */
@Data
public class BinPickingParam {
    private String tradeId; // 请求唯一id，核对用	String	返回参数中直接返回该字段，两边核对用
    private List<BinPickingOrder> orderList; // 订单数据集合
    private List<BinPickingVehicleModel> vehicleModelList; // 车型数据集合
}
