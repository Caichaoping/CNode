package com.cc.cnode.model.entity;

import com.cc.cnode.util.FormatUtils;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

/**
 * 注释：用户
 * 作者：菠菜 on 2016/4/7 09:12
 * 邮箱：971859818@qq.com
 */
public class User {

    @SerializedName("loginname")
    private String loginName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String githubUsername;

    @SerializedName("create_at")
    private DateTime createAt;

    private int score;

    @SerializedName("recent_topics")
    private List<TopicSimple> recentTopicList;

    @SerializedName("recent_replies")
    private List<TopicSimple> recentReplyList;

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

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }

    public DateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(DateTime createAt) {
        this.createAt = createAt;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<TopicSimple> getRecentTopicList() {
        return recentTopicList;
    }

    public void setRecentTopicList(List<TopicSimple> recentTopicList) {
        this.recentTopicList = recentTopicList;
    }

    public List<TopicSimple> getRecentReplyList() {
        return recentReplyList;
    }

    public void setRecentReplyList(List<TopicSimple> recentReplyList) {
        this.recentReplyList = recentReplyList;
    }

}
