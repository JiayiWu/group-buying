package com.mergeorder.presentation.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.ConversationTmp;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.activity.ChatActivity;
import com.mergeorder.presentation.adapter.ChatItemAdapter;
import com.mergeorder.presentation.adapter.ChatItemAdapterTmp;
import com.mergeorder.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 17-4-23.
 * <p>
 * 私聊
 */
public class ChatFragment extends Fragment {

    private View view;

    private SwipeMenuListView chatList;

    private ChatItemAdapter adapter;

    private List<EMConversation> conversationList;

    private List<UserInfo> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);

        init();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

//        loadConversations();
    }

    private void init() {
        initUIComponents();

        injectDemoData();
    }

    private void injectDemoData() {
        List<ConversationTmp> data = new ArrayList<>();

        data.add(new ConversationTmp(R.mipmap.portrait0, "李华", "5月7日", "好的, 一会见", 1));
        data.add(new ConversationTmp(R.mipmap.portrait2, "张小凡", "5月6日", "是下午四点到大连北的", 3));
        data.add(new ConversationTmp(R.mipmap.portrait3, "叶子", "4月29日", "我已经在梁圆门口了哈", 0));
        data.add(new ConversationTmp(R.mipmap.portrait4, "孤独风中一匹狼", "4月15日", "好的谢谢, 合作愉快", 0));
        data.add(new ConversationTmp(R.mipmap.portrait5, "王东", "4月15日", "是美森5月周末的课, 有兴趣吗~", 0));

        chatList.setAdapter(new ChatItemAdapterTmp(getActivity(), data));
    }

    private void initUIComponents() {
        chatList = (SwipeMenuListView) view.findViewById(R.id.chat_list);

        SwipeMenuCreator creator = menu -> {
            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(view.getContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(200);
            deleteItem.setTitle("删除");
            deleteItem.setTitleSize(18);
            deleteItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(deleteItem);
        };

        // set creator
        chatList.setMenuCreator(creator);
        // Left
        chatList.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    /**
     * 加载所有回话
     */
    private void loadConversations() {
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();

        conversationList = new ArrayList<>();
        StringBuilder param = new StringBuilder();
        for (Map.Entry<String, EMConversation> entry : conversations.entrySet()) {
            conversationList.add(entry.getValue());

            EMMessage message = entry.getValue().getLastMessage();
            param.append(getChatTarget(message)).append(" ");
        }

        loadUserList(param.toString());
    }

    private void loadUserList(String phones) {
        String url = "/user/phones/info";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("param", phones)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            userList = new Gson().fromJson(result.get("object"), new TypeToken<List<UserInfo>>() {
                            }.getType());

                            adapter = new ChatItemAdapter(getActivity(), conversationList, userList);
                            chatList.setAdapter(adapter);

                            addListeners();
                        }
                    }
                });
    }

    private void addListeners() {
        chatList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), ChatActivity.class);

            intent.putExtra("userInfo", userList.get(position));

            startActivity(intent);
        });

        chatList.setOnMenuItemClickListener((position, menu, index) -> {
            if (index == 0) {
                conversationList.remove(position);

                adapter.notifyDataSetChanged();
            }

            // false : close the menu; true : not close the menu
            return false;
        });
    }

    /**
     * 获取聊天对象
     *
     * @return 聊天对象的手机号
     */
    private String getChatTarget(EMMessage message) {
        if (((MyApplication) getContext().getApplicationContext()).getUser().getTelephone().equals(message.getFrom())) {
            return message.getTo();
        }

        return message.getFrom();
    }
}
