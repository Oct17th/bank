package com.bank.controller.struts1.action;

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
public class LogoutAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("name");
        return null;
    }
}
