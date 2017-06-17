package com.bank.service;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.PropertiesNotFoundException;
import com.bank.exception.UserException;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.UserServiceImpl;
import com.bank.util.PropertiesUtil;

import java.io.File;
import java.util.Scanner;

/**
 * @author YiJie  2017/6/17
 **/
public class BankTest {
    private static String drawLine(String separator) {//3*7+11+4*7+11+7的长度是数出来的
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 7; i++) {
                stringBuffer.append("─");
            }
            stringBuffer.append(separator);
        }
        for (int i = 0; i < 11; i++) {
            stringBuffer.append("─");
        }
        stringBuffer.append(separator);
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 7; i++) {
                stringBuffer.append("─");
            }
            stringBuffer.append(separator);
        }
        for (int i = 0; i < 11; i++) {
            stringBuffer.append("─");
        }
        stringBuffer.append(separator);
        for (int i = 0; i < 7; i++) {
            stringBuffer.append("─");
        }
        return new String(stringBuffer);
    }

    public static void main(String[] args) throws PropertiesNotFoundException {
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
        PropertiesUtil propertiesUtil = new PropertiesUtil(path, true);
        UserService userService = new UserServiceImpl(propertiesUtil);
        AccountService accountService = new AccountServiceImpl(propertiesUtil);
        System.out.println("┌" + drawLine("┬") + "┐");
        System.out.println("|操作号\t│1\t\t|2\t\t|3\t\t\t|4\t\t|5\t\t|6\t\t|7\t\t|8\t\t\t|9\t\t|");
        System.out.println("├" + drawLine("┼") + "┤");
        System.out.println("|功能\t|注册\t|登录\t|查询余额\t|存款\t|取款\t|转账\t|注销\t|保存退出\t|退出\t|");
        System.out.println("└" + drawLine("┴") + "┘");
        Scanner in = new Scanner(System.in);
        String name = null;
        while (true) {
            System.out.print("选择操作：");
            switch (in.next().charAt(0) - '0') {//只读一个int
                case 1:
                    System.out.println("请输入用户名密码：");//TODO 密码错误可重新输入一次
                    try {
                        userService.register(in.next(), in.next());
                    } catch (UserException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("注册成功！");
                    break;
                case 2:
                    System.out.println("请输入用户名密码：");
                    String name0 = in.next();
                    if (name0.equals(name)) {//name0未赋值为空，name未赋值为null
                        System.out.println("用户已登录！");
                    }
                    try {
                        userService.login(name0, in.next());
                    } catch (UserException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    name = name0;
                    System.out.println("欢迎" + name + "用户！");
                    break;
                case 3:
//                    System.out.println("请输入用户名：");
                    if (name == null) {
                        System.out.println("用户未登录!");
                        break;
                    }
                    System.out.println("账户余额：" + accountService.inquiry(name));
                    break;
                case 4:
                    if (name == null) {
                        System.out.println("用户未登录！");
                        break;
                    }
                    System.out.print("请输入存款金额：");
                    try {
                        accountService.deposit(name, in.nextDouble());
                    } catch (InvalidAmountException e) {
                        System.out.println("存款金额不能为负数！");
                        break;
                    }
                    System.out.println("账户余额：" + accountService.inquiry(name));
                    break;
                case 5:
                    if (name == null) {
                        System.out.println("用户未登录！");
                        break;
                    }
                    System.out.print("请输入取款金额：");
                    try {
                        accountService.withdrawals(name, in.nextDouble());
                    } catch (InvalidAmountException e) {
                        System.out.println("取款金额不能为负数！");
                        break;
                    } catch (AccountOverDrawnException e) {
                        System.out.println("账户余额不足！");
                        break;
                    }
                    System.out.println("账户余额：" + accountService.inquiry(name));
                    break;
                case 6:
                    if (name == null) {
                        System.out.println("用户未登录！");
                        break;
                    }
                    System.out.print("请输入收账人用户名：");
                    String transferTo = in.next();
                    System.out.print("请输入转账金额：");
                    try {
                        accountService.transfer(name, transferTo, in.nextDouble());
                    } catch (InvalidAmountException e) {
                        System.out.println("转账金额不能为负数！");
                        break;
                    } catch (AccountOverDrawnException e) {
                        System.out.println("账户余额不足！");
                        break;
                    } catch (UserException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("账户余额：" + accountService.inquiry(name));
                    break;
                case 7:
                    if (name == null) {
                        System.out.println("用户未登录！");
                        break;
                    }
                    System.out.println("退出" + name + "账号！");
                    name = null;
                    break;
                case 8:
                    propertiesUtil.store("");//选择保存退出系统时将修改后的Properties写入文件保存
                    System.exit(0);
                case 9:
                    System.exit(0);
                default:
                    System.out.println("输入操作号不合法！");
                    break;

            }
        }
    }
}
