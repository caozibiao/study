package com.java.study.collection;

/**
 * @Author zibiao cao
 * @date 2020/12/10 17:08
 * @Version 1.0
 */
public class HashOverrideObj {
    @Override
    public int hashCode() {
        int h = "HashOverrideObj".hashCode();
        return h ^ (h >>> 16);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
