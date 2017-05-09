package com.mergeorder.presentation.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.Comment;
import com.mergeorder.model.CommentPO;
import com.mergeorder.model.TopicDetail;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.adapter.SlideAdapter;
import com.mergeorder.presentation.view.CommentListView;
import com.mergeorder.presentation.view.MyImageView;
import com.mergeorder.presentation.view.Portrait;
import com.mergeorder.presentation.view.TypeTextView;
import com.mergeorder.util.HttpUtil;
import com.mergeorder.util.TimeUtil;
import so.orion.slidebar.GBSlideBar;

/**
 * 帖子详情 界面
 */
public class TopicActivity extends AppCompatActivity {

    private static final String[] URL = new String[]{
            "/car/order/index",
            "/class/order/index",
            "/mail/order/index",
            "/buy/order/index"
    };

    private static final String[] SUCCESS_URL = new String[]{
            "/car/order/success",
            "/class/order/success",
            "/mail/order/success",
            "/buy/order/success"
    };

    private static final String[] CANCEL_URL = new String[]{
            "/car/order/delete",
            "/class/order/delete",
            "/mail/order/delete",
            "/buy/order/delete"
    };

    private Portrait portrait;

    private TextView tv_name, tv_time;

    private TypeTextView tv_type;

    private TextView tv_title, tv_content;

    private MyImageView[] images;

    private GBSlideBar gbSlideBar;

    private Button btn_changeState;

    private CommentListView lv_comment;

    private EditText et_comment;

    private ImageView btn_send;

    /**
     * topic id
     */
    private int id;

    /**
     * topic 类型
     */
    private int type;

    private TopicDetail topicDetail;

    /**
     * 记录要回复的评论id
     */
    private int replyId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        init();
    }

    @Override
    public void onBackPressed() {
        if (replyId != -1) {
            resetComment();
        } else {
            super.onBackPressed();
        }
    }

    private void init() {
        id = getIntent().getIntExtra("id", -1);
        type = getIntent().getIntExtra("type", 0);

        initUIComponents();
        addListeners();
        showData();
    }

    private void initUIComponents() {
        initToolbar();
        initStateSlideBar();

        portrait = (Portrait) findViewById(R.id.topic_detail_portrait);

        tv_name = (TextView) findViewById(R.id.topic_detail_name);
        tv_time = (TextView) findViewById(R.id.topic_detail_time);
        tv_type = (TypeTextView) findViewById(R.id.topic_detail_type);

        tv_title = (TextView) findViewById(R.id.topic_detail_title);
        tv_content = (TextView) findViewById(R.id.topic_detail_content);

        images = new MyImageView[]{
                (MyImageView) findViewById(R.id.topic_detail_image_0),
                (MyImageView) findViewById(R.id.topic_detail_image_1),
                (MyImageView) findViewById(R.id.topic_detail_image_2)
        };

        lv_comment = (CommentListView) findViewById(R.id.topic_detail_comment_list);

        et_comment = (EditText) findViewById(R.id.topic_detail_comment);
        // 迷之自动获取焦点
        et_comment.clearFocus();

        btn_send = (ImageView) findViewById(R.id.topic_detail_comment_new);
    }

    private void initStateSlideBar() {
        gbSlideBar = (GBSlideBar) findViewById(R.id.slide_bar);

        btn_changeState = (Button) findViewById(R.id.topic_detail_change_state);

        Resources resources = getResources();
        SlideAdapter mAdapter = new SlideAdapter(resources, new int[]{
                R.drawable.selector_ic_check,
                R.drawable.selector_ic_more,
                R.drawable.selector_ic_cancel
        });

        mAdapter.setTextColor(new int[]{
                Color.GRAY, Color.BLUE, Color.RED
        });

        gbSlideBar.setAdapter(mAdapter);
        gbSlideBar.setPosition(1);
        gbSlideBar.setOnGbSlideBarListener(this::onStateChange);
    }

    /**
     * 重置评论输入框
     */
    private void resetComment() {
        et_comment.clearFocus();
        et_comment.setText("");
        et_comment.setHint("添加评论");
        replyId = -1;
    }

    private void addListeners() {
        btn_send.setOnClickListener(v -> {
            String comment = et_comment.getText().toString().trim();

            if ("".equals(comment)) {
                Toast.makeText(this, "评论内容不能为空", Toast.LENGTH_SHORT).show();

                return;
            }

            onAddComment(comment);
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.topic_detail_toolbar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(e -> onBackPressed());
    }

    private void showData() {
        Ion.with(this)
                .load(HttpUtil.BASE_URL + URL[type])
                .setBodyParameter("id", String.valueOf(id))
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            topicDetail = new Gson().fromJson(result.get("object"), TopicDetail.class);

                            show();
                        } else {
                            Toast.makeText(this, result.get("reason").getAsString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void show() {
        portrait.setUserId(topicDetail.getUserid()).setUrl(topicDetail.getUsericonurl()).show();

        tv_name.setText(topicDetail.getUsername());
        tv_time.setText(TimeUtil.getFormatTime(topicDetail.getTime()));

        tv_type.setTopicType(type).setType(topicDetail.getType()).show();

        tv_title.setText(topicDetail.getTitle());
        tv_content.setText(topicDetail.getContent());

        String[] imgPath = topicDetail.getImgPath();
        for (int i = 0; i < imgPath.length; i++) {
            images[i].setUrl(imgPath[i]).show();
        }

        lv_comment.setData(topicDetail.getComments());

        if (topicDetail.getUserid() == ((MyApplication) getApplicationContext()).getId()) {
            gbSlideBar.setVisibility(View.VISIBLE);
        }
    }

    private void onAddComment(String comment) {
        if (((MyApplication) getApplicationContext()).getId() == -1) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();

            return;
        }

        String url;
        if (replyId == -1) {
            url = "/message/leave";
        } else {
            url = "/message/reply";
        }

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("orderid", String.valueOf(topicDetail.getId()))
                .setBodyParameter("orderType", String.valueOf(type))
                .setBodyParameter("content", comment)
                .setBodyParameter("replyid", String.valueOf(replyId))
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            CommentPO commentPO = new Gson().fromJson(result.get("object"), CommentPO.class);

                            lv_comment.addComment(commentPO);

                            resetComment();
                        } else {
                            Toast.makeText(this, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "请检查网络连接", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void reply(Comment comment) {
        et_comment.requestFocus();
        et_comment.setHint("回复:" + comment.getFormName());
        replyId = comment.getFormid();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void onStateChange(int state) {
        if (state == 1) {
            return;
        }

        String url;
        if (state == 0) {
            url = SUCCESS_URL[type];
        } else {
            url = CANCEL_URL[type];
        }

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("id", id + "")
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();

                            onBackPressed();
                        } else {
                            Toast.makeText(this, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
