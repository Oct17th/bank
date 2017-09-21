package com.bank.controller.action.form;

import org.apache.struts.action.ActionForm;

/**
 * @author YiJie 2017/8/30.
 */
public class TransferForm extends ActionForm {
    private String transferUser;

    private Float account;

    public String getTransferUser() {
        return transferUser;
    }

    public void setTransferUser(String transferUser) {
        this.transferUser = transferUser;
    }

    public Float getAccount() {
        return account;
    }

    public void setAccount(Float account) {
        this.account = account;
    }
}
