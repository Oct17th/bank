package com.bank.exception;

/**
 * 配置文件没有找到异常。
 * 配置文件路径错误。
 *
 * @author YiJie  2017/6/17
 **/
public class PropertiesNotFoundException extends Exception {
    public PropertiesNotFoundException(String path) {
        super(path);
    }
}
