package com.example.hotel.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * @author qzb
 * @date 2020/2/21
 */
@Slf4j
@Component
public class MD5Util {
    /**
     * MD5转码
     *
     * @param s
     * @param lower
     * @param charset
     * @return
     */
    public static String MD5(String s, boolean lower, String charset) {
        if (StringUtils.isEmpty(s) || StringUtils.isBlank(s)) {
            return null;
        }

        if (StringUtils.isEmpty(charset)) {
            charset = "UTF-8";
        }

        char hexDigits[] =
                {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String md5 = new String(str);
            if (lower) {
                return md5.toLowerCase();
            }
            return md5;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * MD5解码
     *
     * @param s
     * @param lower
     * @return
     */
    public static String MD5(String s, boolean lower) {
        return MD5(s, lower, null);
    }

    public static String MD5(String s) {
        return MD5(s, false);
    }

    public static String getHashCode(Object object) throws IOException {
        if (object == null) {
            return "";
        }

        String ss = null;
        ObjectOutputStream s = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            s = new ObjectOutputStream(bo);
            s.writeObject(object);
            s.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
                s = null;
            }
        }
        ss = MD5(bo.toString());
        return ss;
    }

    /**
     * 获取文件MD5值
     *
     * @param file
     * @return
     */
    public static String fileMD5Uppercase(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException();
        }
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            String md5 = DigestUtils.md5Hex(is).toUpperCase();
            return md5;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static String inputStreamMD5Uppercase(InputStream is) {
        try {
            String md5 = DigestUtils.md5Hex(is).toUpperCase();
            return md5;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    /**
     * md5和sha-1混合加密
     *
     * @param pwd 要加密的内容
     * @return String md5和sha-1混合加密之后的密码
     */
    public static String md5AndSha(String pwd) {
        return sha(md5(pwd));
    }


    /**
     * md5加密
     *
     * @param pwd 需要加密的密码
     * @return String  md5加密之后的密码
     */
    public static String md5(String pwd) {
        return encrypt(pwd, "md5");
    }


    /**
     * sha-1加密
     *
     * @param pwd 需要加密的密码
     * @return sha-1加密之后的密码
     */
    public static String sha(String pwd) {
        return encrypt(pwd, "sha-1");
    }


    /**
     * md5或者sha-1加密
     *
     * @param pwd       要加密的内容
     * @param algorithm 加密算法名称：md5/sha-1，
     * @return String  md5/sha-1加密后的结果
     */
    private static String encrypt(String pwd, String algorithm) {
        if (pwd == null || "".equals(pwd.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithm == null || "".equals(algorithm.trim())) {
            algorithm = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithm);
            m.update(pwd.getBytes("UTF8"));
            byte s[] = m.digest();
            return hex(s);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.error("加密异常", e);
        }
        return encryptText;
    }


    /**
     * byte[]字节数组 转换成 十六进制字符串
     *
     * @param arr 要转换的byte[]字节数组
     * @return String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


    /**
     * 生成随机Code
     *
     * @param size 随机code长度
     * @return 随机code
     */
    public static String createSecureCode(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(randomChar());
        }
        return builder.toString();
    }

    /**
     * |生成盐|
     *
     * @return
     */
    public static String createSalt() {
        return createSecureCode(16);
    }

    /**
     * 种子
     *
     * @return char
     */
    private static char randomChar() {
        //1：定义验证码需要的字母和数字
        String string = "QWERasdfTYUIqwerOPASzxcvDFGHtyuiopJKLZghjklXCVBbnmNM0123456789";
        //2：定义随机对象
        Random random = new Random();
        return string.charAt(random.nextInt(string.length()));
    }


    /**
     * |生成含有随机盐的密码|
     *
     * @param password 要加密的密码
     * @return String 含有随机盐的密码
     */
    public static String getSaltMd5AndSha(String password, String salt) {
        return md5AndSha(password + salt);
    }


    /**
     * 验证密码
     *
     * @param password 原密码
     * @param password 加密之后的密码
     * @return boolean true表示和原密码一致   false表示和原密码不一致
     */
    public static boolean volidatePassword(String password, String passwordMd5, String salt) {
        String encrypPwd = getSaltMd5AndSha(password, salt);
        return encrypPwd.equals(passwordMd5);
    }

    /**
     * 生成用户ID
     *
     * @return
     */
    public static Long createNewUserId() {
        String code = getRandom(2) + String.valueOf(System.currentTimeMillis())
                + getRandom(1);
        return Long.parseLong(code);
    }

    /**
     * 生成团队成员ID
     *
     */
    public static Long createNewMemberId() {
        return createNewUserId();
    }

    /**
     * 生成指定位数的随机数
     *
     * @param length
     * @return
     */
    public static String getRandom(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

//    public static void main(String[] args) throws UnknownHostException {
//        // 原密码
//        String pwd = "123";
//        // 盐
//        String salt = createSalt();
//
//        // 获取加盐后的MD5值
//        String passwordMd5 = MD5Util.getSaltMd5AndSha(pwd, salt);
//        System.out.println("盐：" + salt);
//        System.out.println("盐1：" + String.valueOf(System.currentTimeMillis() / 1000).substring(2, 10));
//        System.out.println("盐2：" + String.valueOf(InetAddress.getLocalHost().getHostAddress()).replace(".", ""));
//        System.out.println("加盐后MD5：" + passwordMd5);
//        System.out.println("是否是同一字符串:" + MD5Util.volidatePassword(pwd, passwordMd5, salt));
//
//        System.out.println("----------");
//
//        System.out.println("A:" + getRandom(2));
//        String code1 = String.valueOf(System.currentTimeMillis() / 1000).substring(2, 10);
//        System.out.println(code1);
//        Random r2 = new Random();
//        int i2 = r2.nextInt();
//        System.out.println(createNewUserId());
//    }


}
