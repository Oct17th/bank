package com.bank.controller.struts1.action;

import com.bank.controller.struts1.form.TransferForm;
import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.UserException;
import com.bank.service.AccountService;
import com.bank.service.impl.AccountServiceImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 存款
 *
 * @author YiJie 2017/9/10.
 */
public class TransferAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        TransferForm transferForm = (TransferForm) form;
        AccountService accountService = new AccountServiceImpl();
        ServletOutputStream out = response.getOutputStream();
        Float account;
        try {
            account = accountService.transfer(name, transferForm.getTransferUser(), transferForm.getAccount());
        } catch (InvalidAmountException | AccountOverDrawnException | UserException e) {
            out.print(e.getMessage());
            return null;
        }
        out.print(account);
        return null;
    }
}
