package com.java.study.spring.transactional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zibiao cao
 * @date 2020/11/19 10:04
 * @Version 1.0
 */

public class BalanceTransaction {
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transfer() {
        //Balance balance = new Balance();

    }
}
