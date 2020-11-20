package com.haier.tms.request;

import lombok.Data;

import java.util.List;

/**
 * Description: 订单
 * Created By xxm
 */
@Data
public class BinPickingOrder {
    private String orderCode; // 订单号
    private Integer unloadingSequence; // 卸货顺序, Integer数越小越先卸货(先进先出顺序)
    private List<BinPickingGood> goodList; // 货物数据集合
}
