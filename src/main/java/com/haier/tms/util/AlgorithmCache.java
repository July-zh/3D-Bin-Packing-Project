package com.haier.tms.util;

import com.haier.tms.common.Constant;
import com.haier.tms.response.BinPickingResult;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: store the response from native algorithm
 * Created By xxm
 */
public class AlgorithmCache {

    private static final Map<String, BinPickingResult> map = new HashMap<>();

    /**
     * get native algorithm response.
     *
     * @param key request
     * @return
     */
    public static BinPickingResult getValue(String key) {
        BinPickingResult value = map.get(key); // get key from cache
        if (ObjectUtils.isEmpty(value)) {
            return BinPickingResult.builder().msg(Constant.NO_CACHE).build();
        }
        return value;
    }

    /**
     * set cache.
     *
     * @param key   request
     * @param value response from native algorithm
     * @return
     */
    public static String setValue(String key, BinPickingResult value) {
        if (map.containsKey(key)) {
            return Constant.SET_CACHE_FAIL;
        } else {
            map.put(key, value);
            return Constant.SET_CACHE_SUCCESS;
        }
    }
}
