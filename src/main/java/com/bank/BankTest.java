package com.bank;

import com.bank.exception.AccountOverDrawnException;
import com.bank.exception.InvalidAmountException;
import com.bank.exception.PropertiesNotFoundException;
import com.bank.exception.UserException;
import com.bank.service.AccountService;
import com.bank.service.UserService;
import com.bank.service.impl.AccountServiceImpl;
import com.bank.service.impl.UserServiceImpl;
import com.bank.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @author YiJie  2017/6/17
 **/


public class BankTest {
    public static void main(String[] args) throws PropertiesNotFoundException {
//        //获取源码内的properties
//        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
//        //获取classpath下的properties
//        OutputStream outputStream = new FileOutputStream(Thread.currentThread().getClass().getResource("/Bank.properties").getFile());
//        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/Bank.properties");

//        System.out.println("输入账户信息地址：");
//        Scanner in = new Scanner(System.in);

        String path = System.getProperty("user.dir") + File.separator + "Bank.properties";
        try {
            new File(path).createNewFile();
        } catch (IOException e) {
            System.out.println("Bank.properties创建失败！");
        }
        new BankUI(path).bank();
    }

}

class BankUI {

    //    String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
    PropertiesUtil propertiesUtil;

    private BankUI() {
    }

    public BankUI(String path) throws PropertiesNotFoundException {
        this.propertiesUtil = new PropertiesUtil(path, true);
    }

    public BankUI(InputStream inputStream, OutputStream outputStream) throws PropertiesNotFoundException {
        this.propertiesUtil = new PropertiesUtil(inputStream, outputStream, true);
    }

    public void bank() {
        UserService userService = new UserServiceImpl(propertiesUtil);
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
                        propertiesUtil.store(null);//选择保存退出系统时将修改后的Properties写入文件保存
                    } else if (!"N".equals(isSave) && !"n".equals(isSave)) {
                        PrintUtil.wrapper("输入信息错误！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    userService.exitSystem();
            }
        }
    }

    public void account(String name) {
        AccountService accountService = new AccountServiceImpl(propertiesUtil);
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
                        PrintUtil.wrapper("账户余额：" + accountService.deposit(name, Double.parseDouble(in.next())), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
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
                        PrintUtil.wrapper("账户余额：" + accountService.withdrawals(name, Double.parseDouble(in.next())), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
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
                        PrintUtil.wrapper("账户余额：" + accountService.transfer(name, transferTo, Double.parseDouble(in.next())), PrintUtil.WRAPPER_TYPE.METHOD_INFO);
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
                        propertiesUtil.store(null);//选择保存退出系统时将修改后的Properties写入文件保存
                    } else if (!"N".equals(isSave) && !"n".equals(isSave)) {
                        PrintUtil.wrapper("输入信息错误！", PrintUtil.WRAPPER_TYPE.METHOD_ERROR);
                        break;
                    }
                    return;
            }
        }
    }
}


class PrintUtil {
    private static final String methodBorder = "────────────────";
    private static final String accountBorder = "──────────────────────────────";
    //    private static String methodBorder;
    private static String supplementLine = "";

    enum WRAPPER_TYPE {//TODO 枚举的注释说明
        METHOD_INPUT, METHOD_ERROR, METHOD_INFO, METHOD_BEGIN, LOGIN, METHOD_END, INPUT, INFO, LOGOUT
    }

    private PrintUtil() {
    }

    public static void wrapper(String str, WRAPPER_TYPE type) {
        switch (type) {

            case METHOD_BEGIN:
                System.out.println(supplementLine + "┌" + methodBorder.substring(str.length(), methodBorder.length()) + str + "");
                break;
            case METHOD_INPUT:
                System.out.print(supplementLine + "│" + str);
                break;
            case METHOD_ERROR:
                System.out.println(supplementLine + "│");
                System.out.println(supplementLine + "│错误：" + str);
                System.out.println(supplementLine + "└" + methodBorder);
                break;
            case METHOD_INFO:
                System.out.println(supplementLine + "│");
                System.out.println(supplementLine + "│提示：" + str);
                System.out.println(supplementLine + "└" + methodBorder);
                break;
            case METHOD_END:
                System.out.println(supplementLine + "└" + methodBorder);
                break;

            case INPUT:
                System.out.print(supplementLine + str);
                break;
            case INFO:
                System.out.println(supplementLine + str);
                break;

            case LOGIN:
                System.out.println("┌" + accountBorder.substring(str.length(), accountBorder.length()) + str + "账户");
                supplementLine = "│\t";
                break;
            case LOGOUT:
                supplementLine = "";
                System.out.println(supplementLine + "│");
                System.out.println(supplementLine + "│提示：用户" + str + "退出账户管理！");
                System.out.println(supplementLine + "└" + accountBorder);
                break;
        }
    }

    public static void menu(String[] methodList) {
        StringBuffer index = new StringBuffer("│操作号\t│");
        StringBuffer method = new StringBuffer("│业务\t\t│");
        //设置单元格内容
        for (int i = 0; i < methodList.length; i++) {
            index.append(i + (methodList[i].length() > 2 ? "\t\t│" : "\t│"));//TODO 测试通过"\t\t\t│" : "\t\t│"
            method.append(methodList[i] + "\t│");
        }
        System.out.println(supplementLine + "┌" + drawLine(methodList, "┬") + "┐");
        System.out.println(supplementLine + index);
        System.out.println(supplementLine + "├" + drawLine(methodList, "┼") + "┤");
        System.out.println(supplementLine + method);
        System.out.println(supplementLine + "└" + drawLine(methodList, "┴") + "┘");
    }

    private static String drawLine(String[] mark, String separator) {
        int smallSize = 3;//TODO idea内console测试通过长度：7：11
        int bigSize = 7;
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < bigSize; j++) {
            stringBuffer.append("─");
        }
        for (int i = 0, cellLength; i < mark.length; i++) {
            stringBuffer.append(separator);
            cellLength = mark[i].length() > 2 ? bigSize : smallSize;//字符长度为4时，单元格长度为11个─；字符长度为2时，单元格长度为7个─；（忽略分隔符separator）
            for (int j = 0; j < cellLength; j++) {
                stringBuffer.append("─");
            }
        }
        return new String(stringBuffer);
    }

}