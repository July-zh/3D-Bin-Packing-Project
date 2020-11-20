package com.haier.tms.service;

import com.haier.tms.response.BinPickingResult;

/**
 * Description: Algorithm Service
 * Created By xxm
 */
public interface AlgorithmService {

    // get 3d bin packing algorithm
    BinPickingResult getAlgorithm(String binPickingParam);
}
