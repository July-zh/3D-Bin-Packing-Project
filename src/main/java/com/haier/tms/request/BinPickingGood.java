package com.haier.tms.request;

import lombok.Data;

import java.util.List;

/**
 * Description: 货物
 * Created By xxm
 */
@Data
public class BinPickingGood {
    private String materialCode; // 物料编码
    private String setCode; //	套机编码, 非套机 无该字段或者null或者""，有套机同一套机编码相同
    private Integer qty; //	数量
    private Double length; // 长 mm
    private Double width; // 宽 mm
    private Double height; // 高 mm
    private Double weight; // 重量
    private List<BinPickingRestriction> restrictionList; // 可以装箱的装箱限制 一般<=6
}
