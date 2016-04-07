package com.cc.cnode.model.entity;

import com.cc.cnode.util.FormatUtils;
import com.google.gson.annotations.SerializedName;

/**
 * 注释：用户实体类
 * 作者：菠菜 on 2016/4/7 17:41
 * 邮箱：971859818@qq.com
 */
public class Author {
    @SerializedName("loginname")
    private String loginName;

    @SerializedName("avatar_url")
    private String avatarUrl;

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
