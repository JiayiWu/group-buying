package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.bingoogolapple.badgeview.BGABadgeTextView;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.mergeorder.R;
import com.mergeorder.model.Conversation;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.view.Portrait;
import com.mergeorder.util.TimeUtil;

import java.util.List;

/**
 * Created by song on 17-4-28.
 * <p>
 * chat item adapter
 */
public class ChatItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private List<EMConversation> conversationList;

    private List<UserInfo> userInfoList;

    public ChatItemAdapter(Context context, List<EMConversation> conversationList, List<UserInfo> userInfoList) {
        this.inflater = LayoutInflater.from(context);
        this.conversationList = conversationList;
        this.userInfoList = userInfoList;
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public Conversation getItem(int position) {
        EMMessage message = conversationList.get(position).getLastMessage();

        return new Conversation(userInfoList.get(position), message, conversationList.get(position).getUnreadMsgCount());
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

            viewHolder.portrait = (Portrait) convertView.findViewById(R.id.chat_item_portrait);
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

        private Portrait portrait;

        private TextView name;

        private TextView time;

        private TextView content;

        private BGABadgeTextView messageNum;

        private void init(Conversation item) {
            EMMessage message = item.getMessage();
            UserInfo userInfo = item.getUserInfo();
            int unread = item.getUnreadNum();

            portrait.setUserId(Integer.parseInt(userInfo.getId())).setUrl(userInfo.getIconurl()).show();
            name.setText(userInfo.getNickname());
            time.setText(TimeUtil.getFormatTime(message.getMsgTime()));
            content.setText(((EMTextMessageBody) message.getBody()).getMessage());

            if (unread != 0) {
                messageNum.showCirclePointBadge();
                messageNum.showTextBadge(unread + "");
            }
        }
    }
}
