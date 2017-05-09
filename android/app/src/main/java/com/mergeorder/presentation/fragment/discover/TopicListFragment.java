package com.mergeorder.presentation.fragment.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mergeorder.R;
import com.mergeorder.model.TopicList;
import com.mergeorder.presentation.view.TopicListView;

/**
 * Created by song on 17-5-4.
 * <p>
 * topic list fragment
 * 包含topic列表,用于展现精选、搜索结果等
 */
public class TopicListFragment extends Fragment {

    private View view;

    private TopicListView lv_topicList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_topic_list, container, false);

        init();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        showTopicList();
    }

    private void init() {
        lv_topicList = (TopicListView) view.findViewById(R.id.topic_list);

        showTopicList();
    }

    private void showTopicList() {
        TopicList topicList = (TopicList) getArguments().getSerializable("topicList");

        lv_topicList.setData(topicList).show();
    }
}
