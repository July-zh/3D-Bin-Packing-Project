package com.haier.tms.response;

import lombok.Data;

/**
 * Description: 装箱结果货物信息
 * Created By xxm
 */
@Data
public class BinPickingResultGood {
    private String materialCode; // 物料编码
    private String restrictionFlag; // 摆放方向, 1：立放正向；2：立放横向；3：侧放正向；4：侧放横向；5：卧放正向；6：卧放横向；
    private Double x; // x坐标
    private Double y; // y坐标
    private Double z; // z坐标
    private Integer trainIndex; // 货物对于当前车辆装箱步骤的顺序索引
}
