package com.cc.cnode.util.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;

import java.lang.reflect.Type;

/**
 * 注释：时间类适配器－－－用作 Gson解析
 * 作者：菠菜 on 2016/4/7 13:56
 * 邮箱：971859818@qq.com
 */
public class DateTimeTypeAdapter implements JsonSerializer<DateTime>,JsonDeserializer<DateTime> {


    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {

        return new JsonPrimitive(src.toString());
    }

    @Override
    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new DateTime(json.getAsString());
    }

}














