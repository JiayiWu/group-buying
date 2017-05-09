package com.mergeorder.presentation.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.UserList;
import com.mergeorder.presentation.activity.UserActivity;
import com.mergeorder.presentation.adapter.FollowItemAdapter;
import com.mergeorder.util.HttpUtil;

import java.util.List;

/**
 * 我的关注界面
 */
public class MyFollowActivity extends AppCompatActivity {

    private ListView lv_followList;

    private List<UserList> followList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follow);

        init();
    }

    private void init() {
        initUIComponents();

        getData();
    }

    private void initUIComponents() {
        initToolBar();

        lv_followList = (ListView) findViewById(R.id.follow_list);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.follow_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(e -> onBackPressed());
    }

    private void getData() {
        String url = "/user/focus/list";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        followList = new Gson().fromJson(
                                result.get("object"), new TypeToken<List<UserList>>() {
                                }.getType());

                        showData();
                    }
                });
    }

    private void showData() {
        lv_followList.setAdapter(new FollowItemAdapter(this, followList));

        lv_followList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MyFollowActivity.this, UserActivity.class);
            intent.putExtra("id", followList.get(position).getId());
            startActivity(intent);
        });
    }
}