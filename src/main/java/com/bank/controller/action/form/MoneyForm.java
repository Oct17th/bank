package com.bank.controller.action.form;

import org.apache.struts.action.ActionForm;

/**
 * @author YiJie 2017/8/30.
 */
public class MoneyForm extends ActionForm {
    private Float account;

    public Float getAccount() {
        return account;
    }

    public void setAccount(Float account) {
        this.account = account;
    }
}
