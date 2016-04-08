package com.cc.cnode.model.api;

import retrofit.RequestInterceptor;

/**
 * 注释：请求拦截器
 * 作者：菠菜 on 2016/4/8 14:51
 * 邮箱：971859818@qq.com
 */
public class ApiRequestInterceptor implements RequestInterceptor{
    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("User-Agent",ApiDefine.USER_AGENT);
        request.addHeader("Accept",ApiDefine.HTTP_ACCEPT);
    }
}
