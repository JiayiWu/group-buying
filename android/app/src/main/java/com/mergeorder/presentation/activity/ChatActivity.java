package com.mergeorder.presentation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.mergeorder.R;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.adapter.ChatMessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView lv_message;

    private EditText et_input;

    private Button btn_send;

    private ChatMessageAdapter adapter;

    /**
     * 聊天对象的用户信息
     */
    private UserInfo userInfo;

    private List<EMMessage> messageList;

    private EMConversation conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAllMessage();

        // 添加消息监听
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 退出前将所有消息导入数据库
//        EMClient.getInstance().chatManager().importMessages(messageList);
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    private void init() {
        userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");

        initUIComponents();

        addListener();

        loadAllMessage();
    }

    private void initUIComponents() {
        initToolbar();

        lv_message = (ListView) findViewById(R.id.chat_message_container);

        et_input = (EditText) findViewById(R.id.chat_input);

        btn_send = (Button) findViewById(R.id.chat_send);

        sendEnabled(false);
    }

    private void addListener() {
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(et_input.getText().toString())) {
                    sendEnabled(false);
                } else {
                    sendEnabled(true);
                }
            }
        });

        btn_send.setOnClickListener(v -> onSend());

        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
        toolbar.setTitle(userInfo.getNickname());

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    /**
     * 加载所有聊天
     */
    private void loadAllMessage() {
        conversation = EMClient.getInstance().chatManager().getConversation(userInfo.getTelephone(), EMConversation.EMConversationType.Chat, true);

        // 设置当前会话未读数为 0
//        conversation.markAllMessagesAsRead();

        int count = conversation.getAllMessages().size();
        if (count < conversation.getAllMsgCount() && count < 20) {
            // 获取已经在列表中的最上边的一条消息id
            String msgId = conversation.getAllMessages().get(0).getMsgId();
            // 分页加载更多消息，需要传递已经加载的消息的最上边一条消息的id，以及需要加载的消息的条数
            conversation.loadMoreMsgFromDB(msgId, 20 - count);
        }
        // 打开聊天界面获取最后一条消息内容并显示
        if (conversation.getAllMessages().size() > 0) {
            EMMessage message = conversation.getLastMessage();
            EMTextMessageBody body = (EMTextMessageBody) message.getBody();
            // 将消息内容和时间显示出来
//            mContentText.setText(body.getMessage() + " - " + conversation.getLastMessage().getMsgTime());
        }

        if (conversation != null) {
            //获取此会话的所有消息
            messageList = conversation.getAllMessages();
        } else {
            messageList = new ArrayList<>();
        }

        initMessageList();
    }

    private void initMessageList() {
        adapter = new ChatMessageAdapter(this, userInfo, messageList);

        lv_message.setAdapter(adapter);

        lv_message.smoothScrollToPositionFromTop(messageList.size(), 0);
    }

    private void onSend() {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户
        EMMessage message = EMMessage.createTxtSendMessage(et_input.getText().toString(), userInfo.getTelephone());
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);

        onAddMessage(message);
    }

    private void onAddMessage(EMMessage message) {
        messageList.add(message);
        et_input.setText("");
        lv_message.smoothScrollToPositionFromTop(messageList.size(), 0);
        adapter.notifyDataSetChanged();
        System.out.println("ChatActivity.onAddMessage");
    }

    private void sendEnabled(boolean enabled) {
        btn_send.setEnabled(enabled);

        if (enabled) {
            btn_send.setBackgroundResource(R.drawable.bordered_button);
        } else {
            btn_send.setBackgroundResource(R.drawable.bordered_button_disable);
        }
    }

    private EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // 循环遍历当前收到的消息
            for (EMMessage message : messages) {
                if (message.getFrom().equals(userInfo.getTelephone())) {
                    // 设置消息为已读
                    conversation.markMessageAsRead(message.getMsgId());

                    onAddMessage(message);
                }
            }
            Toast.makeText(ChatActivity.this, messages.size(), Toast.LENGTH_SHORT).show();
            System.out.println("ChatActivity.onMessageReceived");
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
            System.out.println("ChatActivity.onCmdMessageReceived");
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
            System.out.println("ChatActivity.onMessageRead");
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
            System.out.println("ChatActivity.onMessageDelivered");
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
            System.out.println("ChatActivity.onMessageChanged");
        }
    };
}
