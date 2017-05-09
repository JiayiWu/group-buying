package com.mergeorder.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.Topic;
import com.mergeorder.model.TopicList;
import com.mergeorder.presentation.adapter.TabAdapter;
import com.mergeorder.presentation.fragment.discover.TopicListFragment;
import com.mergeorder.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体分类的精选界面
 * 对应首页的四个按钮
 */
public class DiscoverTypeActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private String url;

    private String title;

    private String[] type;

    private List<List<Topic>> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_type);

        init();
    }

    private void init() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        type = getIntent().getStringArrayExtra("type");

        initUIComponents();
        getData();
    }

    private void initUIComponents() {
        initToolbar();

        initSwipeRefresh();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        toolbar.setTitle(title);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        swipeRefreshLayout.setRefreshing(true);

        // 下拉刷新topic list
        swipeRefreshLayout.setOnRefreshListener(this::getData);
    }

    private void getData() {
        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            topicList = new Gson().fromJson(result.get("object"),
                                    new TypeToken<List<List<Topic>>>() {
                                    }.getType());

                            showTopicList();

                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }

    private void showTopicList() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for (int i = 0; i < topicList.size(); i++) {
            TopicListFragment fragment = new TopicListFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("topicList", new TopicList(topicList.get(i)));
            fragment.setArguments(bundle);

            fragmentList.add(fragment);

            if (title.equals("拼车")) {
                nameList.add(topicList.get(i).get(0).getType());
            } else {
                nameList.add(type[i]);
            }
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragmentList, nameList));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
    }
}
