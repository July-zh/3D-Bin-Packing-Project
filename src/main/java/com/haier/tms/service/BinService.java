package com.haier.tms.service;

import com.haier.tms.domain.Item;

import java.util.List;

/**
 * Description:
 * Created By xxm
 */
public interface BinService {

    /**
     * 装货物
     *
     * @param item  single item
     * @param pivot 坐标
     * @return true if pack successful
     */
    Boolean putItem(Item item, List<Integer> pivot);
}
