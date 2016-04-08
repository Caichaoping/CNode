package com.cc.cnode.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.cc.cnode.util.codec.DES3;
import com.cc.cnode.util.codec.Digest;
import com.cc.cnode.util.gson.GsonWrapper;

import java.lang.reflect.Type;

/**
 * 注释：SharedPreferences 包装类
 * 作者：菠菜 on 2016/4/7 11:00
 * 邮箱：971859818@qq.com
 */
public final class SharedWrapper {

    private final Context context;
    private final SharedPreferences sp;

    public static SharedWrapper with(Context context, String name) {
        return new SharedWrapper(context, name);
    }

    //根据传入的 name 加密后，获取全局sp
    public SharedWrapper(Context context, String name) {
        this.context = context.getApplicationContext();
        sp = context.getSharedPreferences(getDigestKey(name), Context.MODE_PRIVATE);
    }

    // 获取MD5 加密后的信息
    private String getDigestKey(String key) {
        return Digest.MD5.getMessage(key);
    }

    //　SHA256 再次加密的deviceToken 作为解密秘钥
    private String getSecretKey() {
        return Digest.SHA256.getMessage(DeviceInfo.getDeviceToken(context));
    }

    // 以字符串方式取数据
    private String get(String key, String defValue) {
        try {

            String value = DES3.decrypt(getSecretKey(), sp.getString(getDigestKey(key), ""));
            if (TextUtils.isEmpty(value)) {
                return defValue;
            } else {
                return value;
            }
        } catch (Exception e) {
            return defValue;

        }
    }

    // 以字符串方式存数据
    private void set(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        try {
            editor.putString(getDigestKey(key), DES3.encrypt(getSecretKey(), value));
        } catch (Exception e) {
            editor.putString(getDigestKey(key), "");
        }
        editor.apply();
    }

    // 取 String
    public String getString(String key, String defValue) {
        return get(key, defValue);
    }

    // 存 String
    public void setString(String key, String value) {
        set(key, value);
    }

    // 取 Boolean
    public boolean getBoolean(String key, boolean defValue) {
        return Boolean.parseBoolean(get(key, Boolean.toString(defValue)));
    }

    // 存 Boolean
    public void setBoolean(String key, boolean value) {
        set(key, Boolean.toString(value));
    }

    // 取 Float
    public float getFloat(String key, float defValue) {
        return Float.parseFloat(get(key, Float.toString(defValue)));
    }

    // 存 Float
    public void setFloat(String key, float value) {
        set(key, Float.toString(value));
    }

    // 取 Int
    public int getInt(String key, int defValue) {
        return Integer.parseInt(get(key, Integer.toString(defValue)));
    }

    // 存 Int
    public void setInt(String key, int value) {
        set(key, Integer.toString(value));
    }

    // 取 Long
    public long getLong(String key, long defValue) {
        return Long.parseLong(get(key, Long.toString(defValue)));
    }

    // 存 Long
    public void setLong(String key, long value) {
        set(key, Long.toString(value));
    }

    //　取 Object
    public <T>T getObject(String key, Class<T> clz) {
        String json = get(key, null);
        if (json == null) {
            return null;
        } else {
            try {
                return GsonWrapper.gson.fromJson(json, clz);
            } catch (Exception e) {
                return null;
            }
        }
    }

    //　取 Object
    public <T>T getObject(String key, Type typeOfT) {
        String json = get(key, null);
        if (json == null) {
            return null;
        } else {
            try {
                return GsonWrapper.gson.fromJson(json, typeOfT);
            } catch (Exception e) {
                return null;
            }
        }
    }

    //　存 Object
    public void setObject(String key, Object value) {
        if (value == null) {
            set(key, "");
        } else {
            String json = GsonWrapper.gson.toJson(value);
            set(key, json);
        }
    }

    // 清除sp
    public void clear() {
        sp.edit().clear().apply();
    }


}
