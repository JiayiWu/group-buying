package com.mergeorder.presentation.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

public class SearchActivity extends AppCompatActivity {

    private static final String URL = "/index/search";

    private static final String[] NAME = new String[]{
            "全部", "拼车", "拼课", "拼邮", "拼团"
    };

    private String query;

    private TopicList topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }

    private void init() {
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);

            search(query);
        }

        initUIComponents();
    }

    private void initUIComponents() {
        initToolbar();
    }

    private void initIndicator() {
        TopicList[] temp = getTypedTopic();

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for (int i = 0; i < temp.length; i++) {
            TopicListFragment fragment = new TopicListFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("topicList", temp[i]);
            fragment.setArguments(bundle);

            fragmentList.add(fragment);
            nameList.add(NAME[i]);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.search_viewpager);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragmentList, nameList));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.search_tabs);
        tabs.setViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(query + " 的搜索结果");

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void search(String query) {
        Ion.with(this)
                .load(HttpUtil.BASE_URL + URL)
                .setBodyParameter("search", query)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            topicList = new TopicList(new Gson().fromJson(result.get("object"),
                                    new TypeToken<List<Topic>>() {
                                    }.getType()));

                            initIndicator();
                        }
                    }
                });
    }

    /**
     * 解析topicList，获取分类列表
     *
     * @return TopicList 数组，共5项
     * 分别为 全部、拼课、拼邮、拼车、拼团
     */
    private TopicList[] getTypedTopic() {
        TopicList[] result = new TopicList[]{
                topicList,
                new TopicList(),
                new TopicList(),
                new TopicList(),
                new TopicList()
        };

        result[0] = topicList;

        for (Topic topic : topicList.getTopicList()) {
            // 全部 占用了 0索引
            result[topic.getOrdertype() + 1].addTopic(topic);
        }

        return result;
    }
}
