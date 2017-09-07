package com.bank.dao;

/**
 * DAO实现类型
 *
 * @author YiJie 2017/9/5.
 */
public enum DAOTYPE {
    /**
     * 实现类直属包名（dao.impl下包名），类前缀
     */
    MySQL("mysql.MySQL"), Properties("properties.Prop");

    private String name;

    /**
     * @param name 目前支持MYSQL和Properties
     */
    private DAOTYPE(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
