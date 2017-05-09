package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mergeorder.R;
import com.mergeorder.model.Comment;
import com.mergeorder.presentation.view.CommentListView;
import com.mergeorder.presentation.view.Portrait;
import com.mergeorder.util.TimeUtil;

import java.util.List;

/**
 * Created by song on 17-5-3.
 *
 * comment item adapter
 */
public class CommentItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private List<Comment> data;

    public CommentItemAdapter(Context context, List<Comment> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Comment getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_comment, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.portrait = (Portrait) convertView.findViewById(R.id.comment_item_portrait);
            viewHolder.name = (TextView) convertView.findViewById(R.id.comment_item_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.comment_item_time);
            viewHolder.content = (TextView) convertView.findViewById(R.id.comment_item_content);
            viewHolder.reply = (ImageView) convertView.findViewById(R.id.comment_item_reply);

            viewHolder.reply.setOnClickListener(v -> ((CommentListView) parent).reply(position));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.init(data.get(position));

        return convertView;
    }

    private class ViewHolder {

        /**
         * 头像
         */
        private Portrait portrait;

        /**
         * 发布者昵称
         */
        private TextView name;

        /**
         * 发布时间
         */
        private TextView time;

        /**
         * 内容
         */
        private TextView content;

        /**
         * 回复评论
         */
        private ImageView reply;

        private void init(Comment item) {
            portrait.setUserId(item.getFormid()).setUrl(item.getFormIconUrl()).show();

            name.setText(item.getFormName());
            time.setText(TimeUtil.getFormatTime(item.getTime()));

            if (item.getToid() != -1) {
                content.setText("回复" + item.getToName() + ": " + item.getContent());
            } else {
                content.setText(item.getContent());
            }
        }
    }
}
