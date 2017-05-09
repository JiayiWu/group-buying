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
import com.mergeorder.presentation.adapter.FansItemAdapter;
import com.mergeorder.util.HttpUtil;

import java.util.List;

/**
 * 我的粉丝界面
 */
public class MyFansActivity extends AppCompatActivity {

    private ListView lv_fansList;

    private List<UserList> fansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fans);

        init();
    }

    private void init() {
        initUIComponents();

        getData();
    }

    private void initUIComponents() {
        initToolBar();

        lv_fansList = (ListView) findViewById(R.id.fans_list);
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.fans_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(e -> onBackPressed());
    }

    private void getData() {
        String url = "/user/fans/list";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        fansList = new Gson().fromJson(
                                result.get("object"), new TypeToken<List<UserList>>() {
                                }.getType());

                        showData();
                    }
                });
    }

    private void showData() {
        lv_fansList.setAdapter(new FansItemAdapter(this, fansList));

        lv_fansList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MyFansActivity.this, UserActivity.class);
            intent.putExtra("id", fansList.get(position).getId());
            startActivity(intent);
        });
    }
}