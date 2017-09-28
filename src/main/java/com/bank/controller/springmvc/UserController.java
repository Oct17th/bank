package com.bank.controller.springmvc;

import com.bank.exception.UserException;
import com.bank.po.User;
import com.bank.service.AccountService;
import com.bank.service.ServiceFactory;
import com.bank.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author YiJie 2017/9/28.
 */
@Controller
@SessionAttributes("name")
public class UserController extends ControllerSupport{

    @RequestMapping("/login.do")
    public void login(User user, ModelMap map, PrintWriter pw) {
        String name = user.getName();
        try {
            userService.login(name, user.getPassword());
        } catch (UserException e) {
            pw.print(e.getMessage());
            return;
        }
        map.addAttribute("name",name);
        pw.print(accountService.inquiry(name));
    }

    @RequestMapping("/register.do")
    public void register(User user,String checkPassword, ModelMap map, PrintWriter pw) {
        System.out.println(user);
        System.out.println(checkPassword);
        String name = user.getName();
        try {
            userService.register(name, user.getPassword());
        } catch (UserException e) {
            pw.print(e.getMessage());
            return;
        }
        map.addAttribute("name",name);
        pw.print(accountService.inquiry(name));
    }

    @RequestMapping("/logout.do")
    public void logout(HttpSession session, PrintWriter pw) {
        session.removeAttribute("name");
    }
}
