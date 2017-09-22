package com.bank.view.console.util;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.UserException;
import com.bank.service.AccountService;
import com.bank.service.UserService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.UserServiceImpl;

import java.util.Scanner;

/**
 * @author YiJie 2017/9/22.
 */
public class ConsoleUI {

    //    String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
//    PropertiesUtil propertiesUtil;

    public ConsoleUI() {
    }

//    public ConsoleUI(String path) throws PropertiesNotFoundException {
//        this.propertiesUtil = new PropertiesUtil(path, true);
//    }
//
//    public ConsoleUI(InputStream inputStream, OutputStream outputStream) throws PropertiesNotFoundException {
//        this.propertiesUtil = new PropertiesUtil(inputStream, outputStream, true);
//    }

    public void bank() {
        UserService userService = new UserServiceImpl();
        Scanner in = new Scanner(System.in);
        PrintUtil.wrapper("银行系统：", PrintUtil.WRAPPER_TYPE.INFO);
        String[] methodList = new String[]{"获取菜单", "注册", "登录", "退出"};
        PrintUtil.menu(methodList);
        int method = -1;
        while (true) {
            PrintUtil.wrapper("选择业务：", PrintUtil.WRAPPER_TYPE.INPUT);
            //while (true)循环读取控制台输入用next()最好。用其他的nextXXX(类型)的方式要考虑InputMismatchException，而且无法控制每次写入量。用nextLine要会写入空格
            try {
                method = Integer.parseInt(in.next());
            } catch (NumberFormatException e) {
                PrintUtil.wrapper("错误：输入操作号不合法！", PrintUtil.WRAPPER_TYPE.INFO);
                continue;
            }
            if (method > methodList.length-1 || method < 0) {
                PrintUtil.wrapper("错误：输入操作号不合法！", PrintUtil.WRAPPER_TYPE.INFO);
                continue;
            }
            PrintUtil.wrapper(methodList[method], PrintUtil.WRAPPER_TYPE.METHOD_BEGIN);

            switch (method) {
                case 0:
                    PrintUtil.menu(methodList);
                    PrintUtil.wrapper("", PrintUtil.WRAPPER_TYPE.METHOD_END);
                    break;
                case 1://注册
                    boolean isExist = true;
                    PrintUtil.wrapper("用户名：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String name1 = in.next();
                    try {
                        userService.checkUser(name1);
                    } catch (UserException e) {
                        isExist = false;
                    }
                    if (isExist == true) {
                        PrintUtil.wrapper("已存在用户" + name1 + "!", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("密码：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String pwd1 = in.next();
                    PrintUtil.wrapper("确认密码：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    if (!pwd1.equals(in.next())) {
                        PrintUtil.wrapper("两次输入密码不一致！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    try {
                        userService.register(name1, pwd1);
                    } catch (UserException e) {
                        PrintUtil.wrapper(e.getMessage(), PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("用户" + name1 + "注册成功！", PrintUtil.WRAPPER_TYPE.METHOD_INFO);
                    break;
                case 2://登录
                    PrintUtil.wrapper("用户名：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String name2 = in.next();
//                    if (name2.equals(name)) {//name0未赋值为空，name未赋值为null
//                        PrintUtil.wrapper("用户已登录！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
//                    }//总系统与账户分了两界面做，不退出个人账户无法重新登录，不会出现这种情况
                    PrintUtil.wrapper("密码：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String pwd2 = in.next();
                    try {
                        userService.login(name2, pwd2);
                    } catch (UserException e) {
                        PrintUtil.wrapper(e.getMessage(), PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
//                    name = name2;
                    PrintUtil.wrapper("欢迎" + name2 + "用户！", PrintUtil.WRAPPER_TYPE.METHOD_INFO);
                    PrintUtil.wrapper(name2, PrintUtil.WRAPPER_TYPE.LOGIN);
                    account(name2);
                    PrintUtil.wrapper(name2, PrintUtil.WRAPPER_TYPE.LOGOUT);
                    break;
                case 3://退出
                    PrintUtil.wrapper("是否保存系统信息修改？[Y/N]", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String isSave = in.next();
                    if ("Y".equals(isSave) || "y".equals(isSave)) {
//                        propertiesUtil.store(null);//选择保存退出系统时将修改后的Properties写入文件保存
                    } else if (!"N".equals(isSave) && !"n".equals(isSave)) {
                        PrintUtil.wrapper("输入信息错误！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    userService.logout();
            }
        }
    }

    public void account(String name) {
        AccountService accountService = new AccountServiceImpl();
        Scanner in = new Scanner(System.in);
        PrintUtil.wrapper("个人账户：", PrintUtil.WRAPPER_TYPE.INFO);
        String[] methodList = new String[]{"获取菜单", "查询余额", "存款", "取款", "转账", "退出"};
        PrintUtil.menu(methodList);
        int method = -1;
        while (true) {
            PrintUtil.wrapper("选择业务：", PrintUtil.WRAPPER_TYPE.INPUT);
            //while (true)循环读取控制台输入用next()最好。用其他的nextXXX(类型)的方式要考虑InputMismatchException，而且无法控制每次写入量。用nextLine要会写入空格
            try {
                method = Integer.parseInt(in.next());
            } catch (NumberFormatException e) {
                PrintUtil.wrapper("错误：输入操作号不合法！", PrintUtil.WRAPPER_TYPE.INFO);
                continue;
            }
            if (method > methodList.length-1 || method < 0) {
                PrintUtil.wrapper("错误：输入操作号不合法！", PrintUtil.WRAPPER_TYPE.INFO);
                continue;
            }
            PrintUtil.wrapper(methodList[method], PrintUtil.WRAPPER_TYPE.METHOD_BEGIN);
            switch (method) {//只读一个int
                case 0:
                    PrintUtil.menu(methodList);
//                    PrintUtil.wrapper("", PrintUtil.WRAPPER_TYPE.METHOD_END);break;//列完表自动显示余额
                case 1://查询余额
                    if (name == null) {
                        PrintUtil.wrapper("用户未登录!", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("账户余额：" + accountService.inquiry(name), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
                    break;
                case 2://存款
                    if (name == null) {
                        PrintUtil.wrapper("用户未登录!", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("请输入存款金额：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    try {
                        PrintUtil.wrapper("账户余额：" + accountService.deposit(name, Float.parseFloat(in.next())), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
                        break;
                    } catch (NumberFormatException e) {
                        PrintUtil.wrapper("输入金额不合法！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    } catch (InvalidAmountException e) {
                        PrintUtil.wrapper("存款金额不能为负数或0！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                case 3://取款
                    if (name == null) {
                        PrintUtil.wrapper("用户未登录!", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("请输入取款金额：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    try {
                        PrintUtil.wrapper("账户余额：" + accountService.withdrawals(name, Float.parseFloat(in.next())), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
                        break;
                    } catch (NumberFormatException e) {
                        PrintUtil.wrapper("输入金额不合法！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    } catch (InvalidAmountException e) {
                        PrintUtil.wrapper("取款金额不能为负数或0！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    } catch (AccountOverDrawnException e) {
                        PrintUtil.wrapper("账户余额不足！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                case 4://转账
                    if (name == null) {
                        PrintUtil.wrapper("用户未登录!", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("请输入收账人用户名：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String transferTo = in.next();
                    try {
                        accountService.checkUser(transferTo);
                    } catch (UserException e) {
                        PrintUtil.wrapper(e.getMessage(), PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    PrintUtil.wrapper("请输入转账金额：", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    try {
                        PrintUtil.wrapper("账户余额：" + accountService.transfer(name, transferTo, Float.parseFloat(in.next())), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
                        break;
                    } catch (NumberFormatException e) {
                        PrintUtil.wrapper("输入金额不合法！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    } catch (InvalidAmountException e) {
                        PrintUtil.wrapper("转账金额不能为负数或0！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    } catch (AccountOverDrawnException e) {
                        PrintUtil.wrapper("账户余额不足！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    } catch (UserException e) {
                        PrintUtil.wrapper(e.getMessage(), PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                case 5://退出
                    PrintUtil.wrapper("是否保存系统信息修改？[Y/N]", PrintUtil.WRAPPER_TYPE.METHOD_INPUT);
                    String isSave = in.next();
                    if ("Y".equals(isSave) || "y".equals(isSave)) {
//                        propertiesUtil.store(null);//选择保存退出系统时将修改后的Properties写入文件保存
                    } else if (!"N".equals(isSave) && !"n".equals(isSave)) {
                        PrintUtil.wrapper("输入信息错误！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    return;
            }
        }
    }
}
