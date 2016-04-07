package com.cc.cnode.model.entity;

import com.cc.cnode.R;

/**
 * 注释：文章类型
 * 作者：菠菜 on 2016/4/7 16:46
 * 邮箱：971859818@qq.com
 */
public enum TabType {
    all(R.string.tab_all),

    good(R.string.tab_good),

    share(R.string.tab_share),

    ask(R.string.tab_ask),

    job(R.string.tab_job);


    private int nameId;

    TabType(int nameId){
        this.nameId = nameId;
    }

    public int getNameId(){
        return nameId;
    }

}
