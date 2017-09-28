package com.bank.controller.springmvc;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.PrintWriter;

/**
 * @author YiJie 2017/9/28.
 */
@Controller
@SessionAttributes("name")
public class AccountController extends ControllerSupport {

    @RequestMapping("/inquiry.do")
    public String inquiry(ModelMap map, @ModelAttribute("name") String name) {
        if (name != null) {
            map.addAttribute("account", accountService.inquiry(name));
        }
        return "index";
    }

    @RequestMapping("/deposit.do")
    public void deposit(Float account, @ModelAttribute("name") String name, PrintWriter pw) {
        try {
            account = accountService.deposit(name, account);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
            pw.print(e.getMessage());
            return;
        }
        pw.print(account);
    }

    @RequestMapping("/withdrawals.do")
    public void withdrawals(Float account, @ModelAttribute("name") String name, PrintWriter pw) {
        try {
            account = accountService.withdrawals(name, account);
        } catch (InvalidAmountException | AccountOverDrawnException e) {
            System.out.println(e.getMessage());
            pw.print(e.getMessage());
            return;
        }
        pw.print(account);
    }

    @RequestMapping("/transfer.do")
    public void transfer(Float account, String transferUser, @ModelAttribute("name") String name, PrintWriter pw) {
        try {
            account = accountService.transfer(name, transferUser, account);
        } catch (InvalidAmountException | AccountOverDrawnException | UserException e) {
            System.out.println(e.getMessage());
            pw.print(e.getMessage());
            return;
        }
        pw.print(account);
    }
}
