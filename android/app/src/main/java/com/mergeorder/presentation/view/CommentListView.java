package com.mergeorder.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.mergeorder.R;
import com.mergeorder.model.Comment;
import com.mergeorder.model.CommentPO;
import com.mergeorder.model.User;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.activity.TopicActivity;
import com.mergeorder.presentation.adapter.CommentItemAdapter;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by song on 17-5-3.
 * <p>
 * 评论list
 */
public class CommentListView extends ListView {

    private List<Comment> commentList;

    private CommentItemAdapter adapter;

    public CommentListView(Context context) {
        super(context);

        init();
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        LinearLayout footer = (LinearLayout) ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_footer, null, false);

        addFooterView(footer);
    }

    /**
     * 获取指定id对应的comment的用户名
     *
     * @param id 用户id
     */
    private String getName(int id) {
        if (id == -1) {
            return null;
        }

        for (Comment comment : commentList) {
            if (comment.getFormid() == id) {
                return comment.getFormName();
            }
        }

        return null;
    }

    public CommentListView setData(List<Comment> commentList) {
        this.commentList = commentList;

        if (adapter == null) {
            adapter = new CommentItemAdapter(getContext(), commentList);

            setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        return this;
    }

    public void addComment(CommentPO commentPO) {
        User user = ((MyApplication) getContext().getApplicationContext()).getUser();

        Comment comment = new Comment();
        comment.setId(commentPO.getId());
        comment.setToid(commentPO.getToid());
        comment.setToName(getName(commentPO.getToid()));
        comment.setFormIconUrl(user.getIconUrl());
        comment.setFormName(user.getNickname());
        comment.setTime(commentPO.getTime());
        comment.setContent(commentPO.getContent());

        commentList.add(0, comment);
        adapter.notifyDataSetChanged();
    }

    public void reply(int position) {
        ((TopicActivity) getContext()).reply(commentList.get(position));
    }
}
