package com.haier.tms.domain;

import lombok.Data;

import java.util.List;

/**
 * Description: Bin
 * Created By xxm
 */
@Data
public class Bin {
    private String modelCode; // 车型编号

    private Double length; // 长 mm
    private Double width; // 宽 mm
    private Double height; // 高 mm
    private Double weight; // 重量

    private List<Item> items;  // 全部货物
    private List<Item> available_items;  // 已经装入的货物，当前附近还有可放空间

    private Double cubic; //  限方 m³

    /**
     * 获得装入当前Bin中的货物总体积
     *
     * @return double
     */
    public Double getTotalVolume() {
        Double res = null;
        for (Item item : items) {
            res += item.getVolume();
        }
        return res;
    }

    /**
     * 获得装入当前Bin中的货物总重量
     *
     * @return double
     */
    public Double getTotalWeight() {
        return null;
    }

    public Boolean putFirstItem() {
        return null;
    }

    public Boolean putRestItem() {
        return null;
    }
}
