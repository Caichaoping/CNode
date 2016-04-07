package com.cc.cnode.util.codec;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 注释：加密类
 * 作者：菠菜 on 2016/4/7 11:11
 * 邮箱：971859818@qq.com
 */
public final  class  Digest {

    // 加密模式
    public static final Digest MD5 = new Digest("MD5");
    public static final Digest SHA1 = new Digest("SHA-1");
    public static final Digest SHA256 = new Digest("SHA-256");
    public static final Digest SHA384 = new Digest("SHA-384");
    public static final Digest SHA512 = new Digest("SHA-512");

    private  final MessageDigest md;

    // 根据加密方式获取 Digest
    private Digest(String algorithm){
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
           throw  new IllegalArgumentException(e);
        }
    }

    // 获取utf-8编码方式的加密字符串 --- 步骤 1
    public String getMessage (String input){
        return getMessage(input.getBytes(Charset.forName("UTF-8")));
    }

    //　获取加密字符串   --- 步骤 2
    public  String getMessage(byte[] input){
        byte[]  buffer = getRaw(input);  //　加密输入流
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        // 加密每个字节
        for (byte b :buffer){
            sb.append(Character.forDigit((b >>>4)&15,16));
            sb.append(Character.forDigit(b & 15,16));
        }
        return sb.toString();
    }

    // 给输入流设置编码  --- 步骤 3
    public byte[] getRaw(String input){
        return getRaw(input.getBytes(Charset.forName("UTF-8")));
    }

    //　给输入流加密  --- 步骤 4
    public byte[] getRaw(byte[] input){
        return md.digest(input);
    }






}
