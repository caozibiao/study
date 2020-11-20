package com.java.study.spring.entity.po;


import lombok.Data;


/**
 * <p>
 * 
 * </p>
 *
 * @author caozibiao
 * @since 2020-11-19
 */
@Data
public class Balance {


    private Integer id;

    private String name;

    private Integer balance;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


}
