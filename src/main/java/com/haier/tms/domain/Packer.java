package com.haier.tms.domain;

import lombok.Data;

import java.util.List;

/**
 * Description:
 * Created By xxm
 */
@Data
public class Packer {
    private String orderCode; // 当前货物所属订单号
    private List<Item> items; // 订单中的货物
}
