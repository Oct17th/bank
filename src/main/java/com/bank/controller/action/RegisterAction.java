package com.bank.controller.action;

import com.bank.controller.action.form.UserForm;
import com.bank.service.AccountService;
import com.bank.service.UserService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.UserServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YiJie 2017/8/30.
 */
public class RegisterAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        UserService userService = new UserServiceImpl();
        userService.register(userForm.getName(),userForm.getPassword());
        //TODO 输入判空，try-catch判断是否注册成功
        request.getSession().setAttribute("user",userForm);
        AccountService accountService = new AccountServiceImpl();
        request.setAttribute("account", accountService.inquiry(userForm.getName()));
        return mapping.findForward("index");
    }
}
