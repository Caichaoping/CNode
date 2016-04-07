package com.cc.cnode.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

/**
 * 注释：Gson包装类
 * 作者：菠菜 on 2016/4/7 13:55
 * 邮箱：971859818@qq.com
 */
public class GsonWrapper {

    private GsonWrapper() {}

    public static final Gson gson = new GsonBuilder()
            // 将 org.joda.time.DateTime 解析成字符串
            .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
            .create();

}
