package com.mergeorder.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.mergeorder.R;
import com.mergeorder.model.Topic;
import com.mergeorder.model.TopicList;
import com.mergeorder.presentation.activity.TopicActivity;
import com.mergeorder.presentation.adapter.TopicItemAdapter;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by song on 17-5-3.
 * <p>
 * topic list
 */
public class TopicListView extends ListView {

    private TopicItemAdapter adapter;

    public TopicListView(Context context) {
        super(context);

        init();
    }

    public TopicListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public TopicListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        LinearLayout footer = (LinearLayout) ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_footer, null, false);

        addFooterView(footer);
    }

    public TopicListView setData(List<Topic> topicList) {
        if (adapter == null) {
            adapter = new TopicItemAdapter(getContext(), topicList);

            setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        return this;
    }

    public TopicListView setData(TopicList topicList) {
        return this.setData(topicList.getTopicList());
    }

    public void show() {
        setOnItemClickListener((parent, view, position, id) -> {
            // 调用此方法，而不是直接调用 topicList.get(position)
            // 因为ListView中添加header或footer会导致position增加
            Topic topic = (Topic) parent.getItemAtPosition(position);

            if (topic == null) {
                return;
            }

            Intent intent = new Intent(getContext(), TopicActivity.class);

            intent.putExtra("id", topic.getId());
            intent.putExtra("type", topic.getOrdertype());

            getContext().startActivity(intent);
        });
    }
}
