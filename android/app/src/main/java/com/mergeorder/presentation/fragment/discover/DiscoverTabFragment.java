package com.mergeorder.presentation.fragment.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.Topic;
import com.mergeorder.presentation.view.TopicListView;
import com.mergeorder.util.HttpUtil;

import java.util.List;

/**
 * Created by song on 17-4-26.
 * <p>
 * "精选" 下的tab页
 */
public class DiscoverTabFragment extends Fragment {

    private View view;

    private SwipeRefreshLayout swipeRefreshLayout;

    private TopicListView lv_topicList;

    private String url;

    private List<Topic> topicList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discover_tab, container, false);

        url = getArguments().getString("url");

        initUIComponents();

        return view;
    }

    private void initUIComponents() {
        initSwipeRefresh();

        lv_topicList = (TopicListView) view.findViewById(R.id.topic_list);

        showTopicList();
    }


    /**
     * 设置下拉刷新
     */
    private void initSwipeRefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.discover_swipe_refresh);

        swipeRefreshLayout.setRefreshing(true);

        // 下拉刷新topic list
        swipeRefreshLayout.setOnRefreshListener(this::showTopicList);
    }

    private void showTopicList() {
        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        topicList = new Gson().fromJson(
                                result.get("object"), new TypeToken<List<Topic>>() {
                                }.getType());

                        lv_topicList.setData(topicList).show();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
