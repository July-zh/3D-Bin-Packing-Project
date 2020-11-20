package com.haier.tms.request;

import lombok.Data;

/**
 * Description: 装箱限制
 * Created By xxm
 */
@Data
public class BinPickingRestriction {
    private String flag; // 1：立放正向；2：立放横向；3：侧放正向；4：侧放横向；5：卧放正向；6：卧放横向；
    private Boolean isBear; // 是否承受重
    private Integer bearLevel; // 受重级别
    private Boolean isStack; // 是否堆码限制
    private Integer stackLevel; // 自身最大堆码层数
}