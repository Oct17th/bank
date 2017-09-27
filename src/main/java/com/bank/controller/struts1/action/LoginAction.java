package com.bank.controller.struts1.action;

import com.bank.controller.struts1.form.UserForm;
import com.bank.exception.UserException;
import com.bank.service.AccountService;
import com.bank.service.ServiceFactory;
import com.bank.service.UserService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.UserServiceImpl;
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
public class LoginAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserForm userForm = (UserForm) form;
        String name = userForm.getName();
        UserService userService = ServiceFactory.getUserService();
        ServletOutputStream out = response.getOutputStream();
        try {
            userService.login(name, userForm.getPassword());
        } catch (UserException e) {
            out.print(e.getMessage());
            return null;
        }
        //TODO 输入判空，try-catch判断是否登录成功
        request.getSession().setAttribute("name", name);
        AccountService accountService = ServiceFactory.getAccountService();
        out.print(accountService.inquiry(name));
        return null;
    }
}
