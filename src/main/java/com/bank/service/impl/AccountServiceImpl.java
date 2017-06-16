package com.bank.service.impl;

import com.bank.service.AccountService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Random;

/**
 * 账户业务实现
 *
 * @author YiJie  2017/6/15
 **/
public class AccountServiceImpl implements AccountService {
    Properties bank = new Properties();//k不可重复，重复写入新v。//TODO 无序遍历？

    {//Properties测试
        try {
            URL propURL = getClass().getResource("../../model/Bank.properties");
            InputStream properties = propURL.openStream();//getResourceAsStream(path) == getResource(path).openStream()
//            InputStream properties = getClass().getResourceAsStream("/Bank.properties");//path不以'/'开头时，我们就能获取与当前类所在的路径相同的资源文件，而以'/'开头时可以获取ClassPath根下任意路径的资源 //TODO 此方法‘/’开头，从classpath下开始找文件，否则从classpath目录下的（指定）包内开始找文件
//            InputStream properties = getClass().getClassLoader().getResourceAsStream("Bank.properties");//class.getResource("/") == class.getClassLoader().getResource("")
//            InputStream properties = ClassLoader.getSystemResourceAsStream("Bank.properties");//
            bank.load(properties);//properties文件为空且未加载到classpath中时，会抛NPE
            bank.setProperty("randomTest"+String.valueOf(new Random().nextInt(100)), "3");
            FileOutputStream storeTo = new FileOutputStream(propURL.getFile(), true);//true表示追加文件
            bank.store(storeTo, "class.getResource(\"../../model/Bank.properties\") + load");//TODO 用绝对路径无法写入源文件，写入的是classpath下的文件
            System.out.println("bank = " + propURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inquiry(String name) {

    }

    public void withdrawals(double amount) {

    }

    public void deposit(double amount) {

    }

    public void transfer(String from, String to) {

    }
}
