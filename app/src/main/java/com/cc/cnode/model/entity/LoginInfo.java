package com.cc.cnode.model.entity;

import com.cc.cnode.util.FormatUtils;
import com.google.gson.annotations.SerializedName;

/**
 * 注释：登录信息
 * 作者：菠菜 on 2016/4/8 10:51
 * 邮箱：971859818@qq.com
 */
public class LoginInfo {

    private String id;

    @SerializedName("loginname")
    private String loginName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatarUrl() { // 修复头像地址的历史遗留问题
        return FormatUtils.getCompatAvatarUrl(avatarUrl);
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
