package com.bank.controller.action;

import com.bank.controller.action.form.TransferForm;
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
 * 存款
 *
 * @author YiJie 2017/9/10.
 */
public class TransferAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        UserForm user = (UserForm) session.getAttribute("user");
        AccountService accountService = new AccountServiceImpl();
        TransferForm transferForm = (TransferForm) form;
        request.setAttribute("account", accountService.transfer(user.getName(), transferForm.getTransferUser(), transferForm.getAccount()));
        return mapping.findForward("index");
    }
}
