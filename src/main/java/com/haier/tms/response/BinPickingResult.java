package com.haier.tms.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description: 算法返回的结果
 * Created By xxm
 */
@Data
@Builder
public class BinPickingResult {
    private String tradeId; // 请求唯一id，核对用
    private String status; // 状态 1:成功 0:失败
    private String msg; // 异常信息
    private Integer carNum; // 总车次
    private Integer goodNum; // 货物总数
    private Double totalCapacity; // 总体积 m³
    private Double totalWeight; // 总重量 t
    private List<BinPickingResultTrain> trainList; // 车次信息集合
}