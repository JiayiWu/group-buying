package com.mergeorder.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.mergeorder.R;
import com.mergeorder.model.User;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.view.Portrait;

import java.util.List;

/**
 * Created by song on 17-5-5.
 * <p>
 * chat message adapter
 */
public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private List<EMMessage> messageList;

    /**
     * 聊天对象的用户信息
     */
    private UserInfo chatInfo;

    /**
     * 用户自己的个人信息
     */
    private User user;

    public ChatMessageAdapter(Context context, UserInfo userInfo, List<EMMessage> messageList) {
        this.inflater = LayoutInflater.from(context);
        this.chatInfo = userInfo;
        this.messageList = messageList;

        this.user = ((MyApplication) context.getApplicationContext()).getUser();
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public EMMessage getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        EMMessage message = messageList.get(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();

            if (user.getTelephone().equals(message.getFrom())) {
                convertView = inflater.inflate(R.layout.chat_send, parent, false);

                viewHolder.portrait = (Portrait) convertView.findViewById(R.id.chat_send_portrait);
                viewHolder.text = (BubbleTextView) convertView.findViewById(R.id.chat_send_text);
            } else {
                convertView = inflater.inflate(R.layout.chat_receive, parent, false);

                viewHolder.portrait = (Portrait) convertView.findViewById(R.id.chat_receive_portrait);
                viewHolder.text = (BubbleTextView) convertView.findViewById(R.id.chat_receive_text);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (user.getTelephone().equals(message.getFrom())) {
            viewHolder.portrait.setUserId(user.getId()).setUrl(user.getIconUrl());
        } else {
            viewHolder.portrait.setUserId(Integer.parseInt(chatInfo.getId())).setUrl(chatInfo.getIconurl());
        }

        viewHolder.portrait.show();
        viewHolder.text.setText(((EMTextMessageBody) message.getBody()).getMessage());

        return convertView;
    }

    /**
     * 此处将发送的消息和接受的消息合并
     */
    private class ViewHolder {

        private Portrait portrait;

        private BubbleTextView text;
    }
}
