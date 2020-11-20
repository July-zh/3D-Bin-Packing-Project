package com.haier.tms.common;

import com.haier.tms.domain.Bin;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Description:
 * Created By xxm
 */
public class Task {
    public static final LinkedList<Bin> BINS = new LinkedList<>();
    public static final LinkedList<Bin> PACKERS = new LinkedList<>();
    public static final HashSet<Bin> USED_BINS = new HashSet<>();
}
