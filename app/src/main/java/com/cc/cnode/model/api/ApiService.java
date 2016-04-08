package com.cc.cnode.model.api;

import com.cc.cnode.model.entity.Result;
import com.cc.cnode.model.entity.TabType;
import com.cc.cnode.model.entity.Topic;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * 注释：API请求接口
 * 作者：菠菜 on 2016/4/8 15:00
 * 邮箱：971859818@qq.com
 */
public interface ApiService {

    /**
     *  主题
     */
    @GET("/v1/topics")
    void getTopicList(
           @Query("tab")TabType tab,
           @Query("page") Integer page,
           @Query("limit") Integer limit,
           @Query("mdrender") Boolean mdrender,
           Callback<Result<List<Topic>>> callback
    );
}
