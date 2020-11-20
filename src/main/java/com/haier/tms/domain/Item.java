package com.haier.tms.domain;

import lombok.Data;

import java.util.List;

/**
 * Description:
 * Created By xxm
 */
@Data
public class Item {
    private String orderCode; // 当前货物所属订单号
    private String materialCode; // 物料编码
    private String setCode; //	套机编码, 非套机 无该字段或者null或者""，有套机同一套机编码相同

    private Double length; // 长 mm
    private Double width; // 宽 mm
    private Double height; // 高 mm

    private List<Integer> position; // [x, y, z] 三个坐标点，标示货物在车厢中的位置

    private Double weight; // 重量

    private String currentFlag; // 当前货物的朝向 (1：立放正向；2：立放横向；3：侧放正向；4：侧放横向；5：卧放正向；6：卧放横向)
    private List<String> restrictionFlags; // 朝向限制 i.e. ['1','2']

    private List<Boolean> bearLimits; // 每个朝向是否承重
    private List<Integer> bearLevels; // 每个朝向的承重级别

    private List<Boolean> stackLimits; // 每个朝向的堆码限制

    private List<Integer> availableSide; // 当前货物可以接触的面

    /**
     * 返回货物体积
     *
     * @return double
     */
    public Double getVolume() {
        return this.length * this.width * this.height;
    }

    /**
     * 经过朝向限制后的货物新的长, 宽, 高,属性
     *
     * @return
     */
    public List<Double> currentItemDimension() {
        return null;
    }
}
