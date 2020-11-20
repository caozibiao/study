package com.java.study.spring.mapper;

import com.java.study.spring.entity.po.Balance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caozibiao
 * @since 2020-11-19
 */

@Repository
public interface BalanceMapper {


    int updateById(@Param(value = "balance") Balance balance);
}
