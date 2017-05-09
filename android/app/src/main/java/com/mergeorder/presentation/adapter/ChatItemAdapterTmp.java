package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bingoogolapple.badgeview.BGABadgeTextView;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.mergeorder.R;
import com.mergeorder.model.Conversation;
import com.mergeorder.model.ConversationTmp;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.view.Portrait;
import com.mergeorder.util.TimeUtil;

import java.util.List;

/**
 * Created by song on 17-4-28.
 * <p>
 * chat item adapter
 *
 * 插入数据专用
 */
public class ChatItemAdapterTmp extends BaseAdapter {

    private LayoutInflater inflater;

    private List<ConversationTmp> data;

    public ChatItemAdapterTmp(Context context, List<ConversationTmp> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ConversationTmp getItem(int position) {
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
            convertView = inflater.inflate(R.layout.list_item_chat, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.portrait = (ImageView) convertView.findViewById(R.id.chat_item_portrait);
            viewHolder.name = (TextView) convertView.findViewById(R.id.chat_item_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.chat_item_time);
            viewHolder.content = (TextView) convertView.findViewById(R.id.chat_item_content);
            viewHolder.messageNum = (BGABadgeTextView) convertView.findViewById(R.id.chat_item_message_num);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.init(getItem(position));

        return convertView;
    }

    private class ViewHolder {

        private ImageView portrait;

        private TextView name;

        private TextView time;

        private TextView content;

        private BGABadgeTextView messageNum;

        private void init(ConversationTmp item) {
            int unread = item.getUnreadNum();

            portrait.setBackgroundResource(item.getPortrait());
            name.setText(item.getName());
            time.setText(item.getTime());
            content.setText(item.getContent());

            if (unread != 0) {
                messageNum.showCirclePointBadge();
                messageNum.showTextBadge(unread + "");
            }
        }
    }
}
