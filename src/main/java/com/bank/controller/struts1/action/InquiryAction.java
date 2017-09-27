package com.bank.controller.struts1.action;

import com.bank.service.AccountService;
import com.bank.service.ServiceFactory;
import com.bank.service.impl.AccountServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author YiJie 2017/9/20.
 */
public class InquiryAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if (name != null) {
            AccountService accountService = ServiceFactory.getAccountService();
            request.setAttribute("account", accountService.inquiry(name));
        }
        return mapping.findForward("index");
    }
}
