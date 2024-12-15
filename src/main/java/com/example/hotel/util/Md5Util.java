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

@Slf4j
@Component
public class Md5Util {

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
   * |create a password with salt|
   *
   * @param password user password
   * @return String password with salt
   */
  public static String getSaltMd5AndSha(String password, String salt) {
    return md5AndSha(password + salt);
  }
  /**
   * md5 and sha-1 hybrid encryption
   *
   * @param pwd the content need to be encrypted
   * @return String the password encrypted by md5 and sha-1
   */
  public static String md5AndSha(String pwd) {
    return sha(md5(pwd));
  }


  /**
   * md5 encrypt
   *
   * @param pwd the content need to be encrypted
   * @return String  the password encrypted by md5
   */
  public static String md5(String pwd) {
    return encrypt(pwd, "md5");
  }


  /**
   * sha-1 encrypt
   *
   * @param pwd the content need to be encrypted
   * @return the password encrypted by sha-1
   */
  public static String sha(String pwd) {
    return encrypt(pwd, "sha-1");
  }


  /**
   * md5 or sha-1 encrypt
   *
   * @param pwd       the content need to be encrypted
   * @param algorithm algorithm：md5/sha-1，
   * @return String  md5/sha-1 encrypt result
   */
  private static String encrypt(String pwd, String algorithm) {
    if (pwd == null || "".equals(pwd.trim())) {
      throw new IllegalArgumentException("Please enter the encrypted content");
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
      log.error("Encryption Exception", e);
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
   * |generate salt|
   *
   * @return
   */
  public static String createSalt() {
    return createSecureCode(16);
  }

  /**
   * random Code
   *
   * @param size random size of code
   * @return random code
   */
  public static String createSecureCode(int size) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      builder.append(randomChar());
    }
    return builder.toString();
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
   * create user ID
   *
   * @return
   */
  public static Long createNewUserId() {
    String code = getRandom(2) + String.valueOf(System.currentTimeMillis())
        + getRandom(1);
    return Long.parseLong(code);
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

}
