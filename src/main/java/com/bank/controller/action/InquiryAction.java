package com.bank.controller.action;

import com.bank.controller.action.form.UserForm;
import com.bank.service.AccountService;
import com.bank.service.impl.AccountServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author YiJie 2017/9/20.
 */
public class InquiryAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        UserForm user = (UserForm) session.getAttribute("user");
        if (user != null) {
            AccountService accountService = new AccountServiceImpl();
            request.setAttribute("account", accountService.inquiry(user.getName()));
        }
        return mapping.findForward("index");
    }
}
