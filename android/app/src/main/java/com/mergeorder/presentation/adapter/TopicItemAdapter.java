package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mergeorder.R;
import com.mergeorder.model.Topic;
import com.mergeorder.util.TimeUtil;
import com.mergeorder.util.TypeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by song on 17-4-30.
 * <p>
 * topic item adapter
 */
public class TopicItemAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private List<Topic> data;

    public TopicItemAdapter(Context context, List<Topic> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Topic getItem(int position) {
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
            convertView = inflater.inflate(R.layout.list_item_topic, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.portrait = (ImageView) convertView.findViewById(R.id.topic_item_portrait);
            viewHolder.name = (TextView) convertView.findViewById(R.id.topic_item_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.topic_item_time);
            viewHolder.type = (TextView) convertView.findViewById(R.id.topic_item_type);
            viewHolder.pictureContainer = (LinearLayout) convertView.findViewById(R.id.topic_item_picture_container);
            viewHolder.title = (TextView) convertView.findViewById(R.id.topic_item_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.topic_item_content);
            viewHolder.position = (TextView) convertView.findViewById(R.id.topic_item_position);
            viewHolder.commentsNum = (TextView) convertView.findViewById(R.id.topic_item_comment_num);

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
        private ImageView portrait;

        /**
         * 发布者昵称
         */
        private TextView name;

        /**
         * 发布时间
         */
        private TextView time;

        /**
         * topic类型
         */
        private TextView type;

        /**
         * 图片容器，存储图片
         */
        private LinearLayout pictureContainer;

        /**
         * 标题
         */
        private TextView title;

        /**
         * 内容
         */
        private TextView content;

        /**
         * 位置
         */
        private TextView position;

        /**
         * 评论数量
         */
        private TextView commentsNum;

        private void init(Topic item) {
            name.setText(item.getUsername());
            time.setText(TimeUtil.getFormatTime(item.getTime()));
            type.setText(TypeUtil.getType(item.getOrdertype(), item.getType()));
            title.setText(item.getTitle());
            content.setText(item.getContent());
            position.setText(String.valueOf("来自 " + item.getLocation()));
            commentsNum.setText(String.valueOf("留言 " + item.getLeaveMessageCount()));

            Glide.with(context)
                    .load(Uri.parse(item.getUsericonurl()))
                    .into(portrait);

//            pictureContainer.addView(createPicture());
//            pictureContainer.addView(createPicture());
        }

//        private ImageView createPicture() {
//            int width = pictureContainer.getWidth() - 20;
//
//            ImageView imageView = new ImageView(context);
//            imageView.setImageResource(R.mipmap.bg_me);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(width / 3, 200));
//
//            return imageView;
//        }
    }
}
