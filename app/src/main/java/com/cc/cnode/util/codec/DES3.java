package com.cc.cnode.util.codec;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 注释：DES3加密解密工具类
 * 作者：菠菜 on 2016/4/7 13:42
 * 邮箱：971859818@qq.com
 */
public class DES3 {

    private static final String IV = "01234567";
    private static final String CHARSET = "utf-8";

    //　DES3加密
    public static String encrypt(String secretKey, String plainText) throws Exception {
        return encrypt(IV, secretKey, plainText);
    }

    public static String encrypt(String iv, String secretKey, String plainText) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(CHARSET));
        return Base64.encodeFromBytes(encryptData);
    }



    //　DES3解密
    public static String decrypt(String secretKey, String encryptText) throws Exception {
        return decrypt(IV, secretKey, encryptText);
    }

    public static String decrypt(String iv, String secretKey, String encryptText) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        Key deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] decryptData = cipher.doFinal(Base64.decodeToBytes(encryptText));
        return new String(decryptData, CHARSET);
    }



}
