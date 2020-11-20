package com.haier.tms.request;

import lombok.Data;

/**
 * Description: 车型
 * Created By xxm
 */
@Data
public class BinPickingVehicleModel {
    private String modelCode;    // 车型编号
    private Integer qty;    // 可用数量	Integer	0表示无限制
    private Double cubic; //	限方 m³	Double
    private Double weight; //	限重 t	Double
    private Double length; //	车长mm	Double
    private Double width; //	车宽mm	Double
    private Double height; //	车高mm	Double
}
