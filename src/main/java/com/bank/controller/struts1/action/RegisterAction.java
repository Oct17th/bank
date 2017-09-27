package com.bank.controller.struts1.action;

import com.bank.controller.struts1.form.UserForm;
import com.bank.exception.UserException;
import com.bank.service.AccountService;
import com.bank.service.ServiceFactory;
import com.bank.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YiJie 2017/8/30.
 */
public class RegisterAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserForm userForm = (UserForm) form;//TODO 新写一个form，这里丢失了一个checkPassword数据
        String name = userForm.getName();
        UserService userService = ServiceFactory.getUserService();
        ServletOutputStream out = response.getOutputStream();
        try {
            userService.register(name,userForm.getPassword());
        } catch (UserException e) {
            out.print(e.getMessage());
            return null;
        }
        //TODO 输入判空，try-catch判断是否注册成功
        request.getSession().setAttribute("name",name);
        AccountService accountService = ServiceFactory.getAccountService();
        out.print(accountService.inquiry(name));
        return null;
    }
}
