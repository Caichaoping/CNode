package com.cc.cnode.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 注释：文件资源读取
 * 作者：菠菜 on 2016/4/8 17:20
 * 邮箱：971859818@qq.com
 */
public final  class  ResRawUtils {

    private ResRawUtils() {}

    public static String getString(Context context, int docRawId) {
        try {
            InputStream is = context.getResources().openRawResource(docRawId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            return "文档读取失败。";
        }
    }

}
