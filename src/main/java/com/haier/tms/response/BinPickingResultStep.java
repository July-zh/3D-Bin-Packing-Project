package com.haier.tms.response;

import lombok.Data;

import java.util.List;

/**
 * Description: 装箱步骤
 * Created By xxm
 */
@Data
public class BinPickingResultStep {
    private Integer step; // 装箱步骤
    private Integer qty; // 货物数量
    private String directionNum; // 各方向摆放件数, 描述当前步骤 深度X宽度X高度 各摆放多少个 例如：2*2*1
    private String orderCode; // 所属订单号, 需要用来确认所属订单 此参数需要保证每一步属于同一个物料
    private List<BinPickingResultGood> goodList; // 货物坐标信息
}
