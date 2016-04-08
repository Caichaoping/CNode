package com.cc.cnode.model.api;

import com.cc.cnode.BuildConfig;
import com.cc.cnode.util.gson.GsonWrapper;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * 注释：请求类
 * 作者：菠菜 on 2016/4/8 15:07
 * 邮箱：971859818@qq.com
 */
public final class ApiClient {
    private ApiClient() {}

    public static final ApiService service = new RestAdapter.Builder()
            .setEndpoint(ApiDefine.API_HOST)
            .setConverter(new GsonConverter(GsonWrapper.gson))
            .setRequestInterceptor(new ApiRequestInterceptor())
            .setLogLevel(BuildConfig.DEBUG?RestAdapter.LogLevel.FULL:RestAdapter.LogLevel.NONE)
            .build()
            .create(ApiService.class);
}
