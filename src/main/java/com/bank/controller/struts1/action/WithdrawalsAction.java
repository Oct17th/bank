package com.bank.controller.struts1.action;

import com.bank.controller.struts1.form.MoneyForm;
import com.bank.exception.InvalidAmountException;
import com.bank.service.AccountService;
import com.bank.service.ServiceFactory;
import com.bank.service.impl.AccountServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 存款
 *
 * @author YiJie 2017/9/10.
 */
public class WithdrawalsAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        MoneyForm moneyForm = (MoneyForm) form;
        AccountService accountService = ServiceFactory.getAccountService();
        Float account;
        ServletOutputStream out = response.getOutputStream();
        try {
            account = accountService.withdrawals(name, moneyForm.getAccount());
        } catch (InvalidAmountException e) {
            out.print(e.getMessage());
            return null;
        }
        out.print(account);
        return null;
    }
}
