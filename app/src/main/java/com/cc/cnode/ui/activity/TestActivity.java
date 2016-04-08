package com.cc.cnode.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cc.cnode.R;
import com.cc.cnode.model.api.ApiClient;
import com.cc.cnode.model.entity.Result;
import com.cc.cnode.model.entity.TabType;
import com.cc.cnode.model.entity.Topic;
import com.cc.cnode.util.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * 注释：测试用
 * 作者：菠菜 on 2016/4/8 13:39
 * 邮箱：971859818@qq.com
 */
public class TestActivity extends AppCompatActivity {

    @Bind(R.id.tv_01)
    TextView tv01;

    private List<Topic> topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topics = new ArrayList<>();
        ApiClient.service.getTopicList(TabType.all, 1, 20, true, new Callback<Result<List<Topic>>>() {
            @Override
            public void success(Result<List<Topic>> listResult, Response response) {
                topics = listResult.getData();
                L.d("请求成功数据个数为："+topics.size());
                for (Topic topic:topics){
                    L.d(topic.toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
