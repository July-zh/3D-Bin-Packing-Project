package com.haier.tms.response;

import lombok.Data;

import java.util.List;

/**
 * Description: 装箱车次信息
 * Created By xxm
 */
@Data
public class BinPickingResultTrain {
    private Integer train; // 车次
    private String modelCode; // 车型编号
    private Integer goodNum; // 此车货物总数
    private Double totalCapacity; // 此车总体积 m³
    private Double totalWeight; // 此车总重量 t
    private Double packingRate; // 装箱利用率
    private List<BinPickingResultStep> stepList; // 当前车装箱步骤信息集合
}