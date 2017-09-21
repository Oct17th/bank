package com.bank.dao.util;

import com.bank.exception.PropertiesNotFoundException;

import java.io.*;
import java.util.Properties;

/**
 * 操作配置文件工具类
 *
 * @author YiJie  2017/6/17
 **/
public class PropertiesUtil {
    /**
     * 内部维护一个配置类
     */
    private Properties properties;
    /**
     * 配置文件路径
     */
    private String path;//TODO 设置区分开发环境运行环境
    private InputStream loadFrom = null;
    private OutputStream storeTo;

    public PropertiesUtil() throws PropertiesNotFoundException {
        this(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Bank.properties");
    }

    public static void main(String[] args) {

    }
    /**
     * 构造函数里装入配置文件
     *
     * @param path 配置文件路径
     * @throws PropertiesNotFoundException 配置文件路径错误，没有找到该配置文件
     */
    public PropertiesUtil(String path) throws PropertiesNotFoundException {
        this(path, false);
//        //判断path文件是否存在，是否为配置文件
//        if (!(path.endsWith(".properties") && new File(path).exists())) {
//            throw new PropertiesNotFoundException(path);
//        }
//        this.path = path;
    }

    /**
     * 构造函数里装入配置文件
     *
     * @param path      配置文件路径
     * @param isLoadNow 是否现在加载。若为false默认set或get时加载
     * @throws PropertiesNotFoundException 配置文件路径错误，没有找到该配置文件
     */
    public PropertiesUtil(String path, boolean isLoadNow) throws PropertiesNotFoundException {
        //判断path文件是否存在，是否为配置文件
//        if (!(path.endsWith(".properties") && !new File(path).exists())) {
            path = "E:\\Workspace\\bank\\src\\main\\resources\\Bank.properties";
//        }
//        if (!new File(path).exists()) {
//            throw new PropertiesNotFoundException(path);
//        }
        this.path = path;
        if (isLoadNow) {
            load();
        }
    }

    /**
     * 构造函数里装入配置文件
     *
     * @param inputStream  加载读取文件输入流
     * @param outputStream  存储写入文件输出流
     * @param isLoadNow    是否现在加载。若为false默认set或get时加载
     * @throws PropertiesNotFoundException 配置文件路径错误，没有找到该配置文件
     */
    public PropertiesUtil(InputStream inputStream, OutputStream outputStream, boolean isLoadNow) throws PropertiesNotFoundException {
        this.loadFrom = inputStream;
        this.storeTo = outputStream;
        if (isLoadNow) {
            load();
        }
    }

    /**
     * 加载配置文件
     */
    public void load() {
        try {
            if (loadFrom == null) {
                loadFrom = new FileInputStream(path);
            }
            properties = new Properties();
            properties.load(loadFrom);
        } catch (IOException e) {//构造函数里对path设值做了判断，程序不会出现该异常，故捕获不处理
            e.printStackTrace();
        } finally {
            try {
                loadFrom.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 存入配置文件
     *
     * @param comments 注释
     */
    public void store(String comments) {
        try {
//            if (storeTo == null) {
                // 为保证覆盖写入，而不是追加写入，每次都要新开一个输出流
                storeTo = new FileOutputStream(path, false);//true表示追加文件
//            }
            properties.store(storeTo, comments);
        } catch (IOException e) {//构造函数里对path设值做了判断，程序不会出现该异常，故捕获不处理
            e.printStackTrace();
        } finally {
            try {
                storeTo.close();
                storeTo = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入Properties
     *
     * @param key
     * @param value
     * @return 返回Properties，以便链式操作
     */
    public Properties set(String key, String value) {
        if (properties == null) {
            load();
        }
        properties.setProperty(key, value);
        store(null);
//        load();
        return this.properties;
    }

    /**
     * 读取Properties
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        if (properties == null) {
            load();
        }
        String value = properties.getProperty(key);
//        value = "".equals(value)?null:value;//若为空串返回null
        return value;
    }

    /**
     * 删除Properties
     *
     * @param key
     * @return 返回value
     */
    public String remove(String key) {
        if (properties == null) {
            load();
        }

        store(null);
//        load();
        return (String) properties.remove(key);
    }
}
