package com.bank.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5即Message-Digest Algorithm 5（信息-摘要算法）
 * <p>
 * 消息摘要简介：<br/>
 * 一个消息摘要就是一个数据块的数字指纹。即对一个任意长度的一个数据块进行计算，产生一个唯一指印（对于SHA1是产生一个20字节的二进制数组）。
 * 消息摘要是一种与消息认证码结合使用以确保消息完整性的技术。主要使用单向散列函数算法，可用于检验消息的完整性，和通过散列密码直接以文本形式保存等，
 * 目前广泛使用的算法有MD4、MD5、SHA-1
 * <p>
 * 消息摘要有两个基本属性：<br/>
 * 1.两个不同的报文难以生成相同的摘要<br/>
 * 2.难以对指定的摘要生成一个报文，而可以由该报文反推算出该指定的摘要<br/>
 * <p>
 * 加密与摘要的区别：<br/>
 * 加密后的消息是完整的；具有解密算法，得到原始数据；<br/>
 * 摘要得到的消息是不完整的；通过摘要的数据，不能得到原始数据；<br/>
 * <p>
 * MD5的长度：<br/>
 * 1.原定长度为128位二进制串散列值<br/>
 * 2.后转换为32位16进制串（常用）<br/>
 * 3.网传的16位MD5是取32位MD5的中间16位（去前8位后8位）<br/>
 * <p>
 * MD5算法的特点：<br/>
 * 1.压缩性：任意长度的数据，算出的MD5值长度都是固定的。<br/>
 * 2.容易计算：从原数据计算出MD5值很容易。<br/>
 * 3.抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。<br/>
 * 4.弱抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。<br/>
 * 5.强抗碰撞：想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的。<br/>
 * <p>
 * 个人理解：<br/>
 * MD5算法是对密码进行非随机的散列处理得到密文，处理是单向不可逆的，即密文不可逆运算解密，不可通过密文拿到原数据。
 * 进行信息比对时，是将测试数据MD5加密后的密文，同原数据MD5加密后的密文进行比对，这样一个正向比较的方式。
 * 而非将密文解密出原数据，让测试数据同原数据比对，这样一种逆向的比较方式。
 * 即MD5的比对方式，是密文与密文的比对，非而测试数据与原数据的比对。此方式也保证了使用者只能操作密文，操作全程都无法通过密文得到原数据。
 *
 * @author YiJie  2017/6/15
 **/
public class MD5Util {

    /**
     * MD5解密比对
     *
     * @param text               测试数据
     * @param originalCiphertext 原数据密文
     * @return 比对结果
     */
    public static boolean verify(String text, String originalCiphertext) {
        String ciphertext = originalCiphertext.length() == 32 ? getMD5by16bit(text) : getMD5by16bit(text);
        return ciphertext.equals(originalCiphertext);//originalCiphertext可能为空
    }

    /**
     * 获得16位MD5加密密文
     *
     * @param text 原数据
     * @return 密文
     */
    public static String getMD5by16bit(String text) {
        return getMD5by32bit(text).substring(8, 23);
    }

    /**
     * 获得32位MD5加密密文
     *
     * @param text 原数据
     * @return 密文
     */
    public static String getMD5by32bit(String text) {
        byte[] bytes = encrypt(text);
        StringBuffer sb = new StringBuffer();
        long j;
        String k;
        for (int i = 0; i < bytes.length; i++) {//断言bytes.length=8

            // &0xff位运算。因为byte是有符号位的，此操作是将有符号位byte转为无符号位byte。
            // 此操作等同于if(byte[i]<0)byte[i]+=256;)
            j = Long.valueOf(bytes[i] & 0xff);

            // 进制转换。这里是一个坑！MD5加密将128位的byte数组（8*8bit）转为32位16进制数时，必须一个一个字节的转，即每8位(1字节)2进制数转成2位16进制数
            // 注意不能将byte数组全部拼接成一个长串后，再做进制转换，因为拼接结果为128位，太长，无法用long存储并做计算
            k = Long.toHexString(j);

            // 补0操作。将每8位2进制数转成2位16进制数时，如遇到的8位2进制数是数值小于16的数，转换结果会为1位的16进制数，故需要补0操作。
            // 此操作等同于if(j<16){k="0"+k;或sb.append(0)}
            k = k.length() < 2 ? "0" + k : k;

            // 拼接操作。把数组每1字节换成16进制后的字符串连接起来
            sb.append(k);
        }
        return new String(sb);
    }

    /**
     * 获得128位MD5加密密文
     *
     * @param text 原数据
     * @return 保存128位MD5加密密文的长度为8的byte数组
     */
    private static byte[] encrypt(String text) {
        byte[] buff = null;
        try {
            //1 调用java.securityMessa包下的MessageDigest类，创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            //2 将消息变成byte数组
            byte[] input = text.getBytes();

            //3 计算后获得字节数组,这就是那128位了
            buff = md.digest(input);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buff;
    }
}
