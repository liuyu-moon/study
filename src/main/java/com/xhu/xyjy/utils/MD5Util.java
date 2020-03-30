package com.xhu.xyjy.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 1.MD5加密字符串（32位大写）
 * 2.MD5加密字符串（32位小写）
 * <p>
 * MD5在线加密：https://md5jiami.51240.com/
 * 3.将二进制字节数组转换为十六进制字符串
 * 4.Unicode中文编码转换成字符串
 */
public class MD5Util {
    /**
     * MD5加密字符串（32位小写）
     *
     * @param string 需要进行MD5加密的字符串
     * @return 加密后的字符串（小写）
     */
    public static String md5Encrypt32(String string) {
        byte[] hash;
        try {
            //创建一个MD5算法对象，并获得MD5字节数组,16*8=128位
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        //转换为十六进制字符串
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toLowerCase();
    }

    /**
     * md5加密加强版
     * @param str
     */
    public static String MD55(String str){
        String md5=MD5Util.md5Encrypt32(str);
        return md5.substring(8,20)+md5.substring(0,8)+md5.substring(20,32);
    }
}