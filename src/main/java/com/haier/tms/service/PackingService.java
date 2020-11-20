package com.haier.tms.service;

import com.haier.tms.domain.Bin;
import com.haier.tms.domain.Item;

/**
 * Description:
 * Created By xxm
 */
public interface PackingService {

    /**
     * 装箱
     *
     * @param bin single bin
     * @return true if pack successful
     */
    Boolean pack(Bin bin);
}
