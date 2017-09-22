package com.bank.view.console;

import com.bank.exception.PropertiesNotFoundException;
import com.bank.view.console.util.ConsoleUI;

import java.io.File;
import java.io.IOException;

/**
 * 控制台展示程序
 *
 * @author YiJie  2017/6/17
 **/


public class Console {
    public static void main(String[] args) throws PropertiesNotFoundException {
//        //获取源码内的properties
//        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
//        //获取classpath下的properties
//        OutputStream outputStream = new FileOutputStream(Thread.currentThread().getClass().getResource("/Bank.properties").getFile());
//        InputStream inputStream = Thread.currentThread().getClass().getResourceAsStream("/Bank.properties");

//        System.out.println("输入账户信息地址：");
//        Scanner in = new Scanner(System.in);
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties";
        try {
            new File(path).createNewFile();
        } catch (IOException e) {
            System.out.println("Bank.properties创建失败！");
        }
        new ConsoleUI().bank();
    }

}


