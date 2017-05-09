package com.mergeorder.presentation.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.Topic;
import com.mergeorder.presentation.adapter.TopicItemAdapter;
import com.mergeorder.presentation.view.TopicListView;
import com.mergeorder.util.HttpUtil;

import java.util.List;

/**
 * 我的评论 界面
 */
public class MyCommentActivity extends AppCompatActivity {

    private TopicListView lv_commentList;

    private List<Topic> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);

        init();
    }

    private void init() {
        initUIComponents();

        getData();
    }

    private void initUIComponents() {
        initToolBar();

        lv_commentList = (TopicListView) findViewById(R.id.my_comment_list);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_comment_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(e -> onBackPressed());
    }

    private void getData() {
        String url = "/user/comments/list";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        topicList = new Gson().fromJson(
                                result.get("object"), new TypeToken<List<Topic>>() {
                                }.getType());

                        showData();
                    }
                });
    }

    private void showData() {
        lv_commentList.setAdapter(new TopicItemAdapter(this, topicList));
    }
}
