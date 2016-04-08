package com.cc.cnode.model.entity;

/**
 * 注释：请求结果泛型
 * 作者：菠菜 on 2016/4/8 14:56
 * 邮箱：971859818@qq.com
 */
public class Result<T>  {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
