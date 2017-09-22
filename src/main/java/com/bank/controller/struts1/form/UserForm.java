package com.bank.controller.struts1.form;

import org.apache.struts.action.ActionForm;

/**
 * @author YiJie 2017/8/30.
 */
public class UserForm extends ActionForm {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
