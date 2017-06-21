package com.bank.exception;

/**
 * 操作金额为负数抛出异常
 *
 * @author YiJie  2017/6/15
 **/
public class InvalidAmountException extends Exception {
    public InvalidAmountException(double amount) {
        super(String.valueOf(amount));
    }
}
