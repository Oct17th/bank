package com.bank.exception;

/**
 * 取款时超出余额时抛出异常
 *
 * @author YiJie  2017/6/15
 **/
public class AccountOverDrawnException extends Exception{
    public AccountOverDrawnException(double amount) {
        super(String.valueOf(amount));
    }
}
