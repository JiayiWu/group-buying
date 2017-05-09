package com.mergeorder.presentation.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.util.HttpUtil;

/**
 * 用户信息界面(其他用户)
 */
public class UserActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView iv_bg, iv_portrait, iv_verified;

    private TextView tv_school, tv_sex, tv_successNum;

    private TextView tv_fans_num, tv_focus_num, tv_publish_num, tv_attend_num, tv_comment_num;

    private Button btn_follow, btn_sendMsg;

    /**
     * 用户id
     */
    private int id;

    private UserInfo userInfo;

    /**
     * 标记是否已关注
     */
    private boolean followed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        init();
    }

    private void init() {
        id = getIntent().getIntExtra("id", 1);

        initUIComponents();
        addListeners();
        getData();
    }

    private void initUIComponents() {
        initToolBar();

        iv_bg = (ImageView) findViewById(R.id.user_img_bg);
        iv_portrait = (ImageView) findViewById(R.id.user_img_avatar);

        iv_verified = (ImageView) findViewById(R.id.user_verified);

        tv_school = (TextView) findViewById(R.id.user_school);
        tv_sex = (TextView) findViewById(R.id.user_sex);
        tv_successNum = (TextView) findViewById(R.id.user_success_num);

        tv_fans_num = (TextView) findViewById(R.id.user_fans_num);
        tv_focus_num = (TextView) findViewById(R.id.user_focus_num);
        tv_publish_num = (TextView) findViewById(R.id.user_publish_num);
        tv_attend_num = (TextView) findViewById(R.id.user_attend_num);
        tv_comment_num = (TextView) findViewById(R.id.user_comment_num);

        btn_follow = (Button) findViewById(R.id.user_follow);
        btn_sendMsg = (Button) findViewById(R.id.user_send_message);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(e -> onBackPressed());
    }


    private void addListeners() {
        btn_follow.setOnClickListener(e -> onFollow());

        btn_sendMsg.setOnClickListener(e -> {
            onSendMsg();
        });
    }

    private void getData() {
        String url = "/user/index/info";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("id", id + "")
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        userInfo = new Gson().fromJson(result.get("object"), UserInfo.class);

                        showData();
                    }
                });
    }

    private void showData() {
        toolbar.setTitle(userInfo.getNickname());

        tv_fans_num.setText(String.valueOf(userInfo.getFansCount()));
        tv_focus_num.setText(String.valueOf(userInfo.getFocusCount()));
        tv_publish_num.setText(String.valueOf(userInfo.getPostCount()));
        tv_attend_num.setText(String.valueOf(userInfo.getAttendCount()));
        tv_comment_num.setText(String.valueOf(userInfo.getCommentCount()));

        tv_school.setText(userInfo.getSchool());
        tv_sex.setText(userInfo.getSex());
        tv_successNum.setText(String.valueOf(userInfo.getSuccessCount()));

        if (userInfo.isAuthentication()) {
            iv_verified.setVisibility(View.VISIBLE);
        }

        Glide.with(this)
                .load(Uri.parse(userInfo.getIconurl()))
                .into(iv_portrait);

        Glide.with(this)
                .load(Uri.parse(userInfo.getBackgroundurl()))
                .centerCrop()
                .placeholder(R.mipmap.bg_me)
                .crossFade()
                .into(iv_bg);

        setFollow();
    }

    /**
     * 设置 “关注” 按钮
     */
    private void setFollow() {
        // 若未登录,隐藏 关注 和 发消息 按钮
        if (!((MyApplication) getApplicationContext()).isLogin()) {
            btn_follow.setVisibility(View.GONE);
            btn_sendMsg.setVisibility(View.GONE);
        }

        String url = "/user/relationship/judge";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("fansid", id + "")
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("object").getAsBoolean()) {
                            followed = true;

                            showFollow();
                        }
                    }
                });
    }

    private void showFollow() {
        if (followed) {
            btn_follow.setText(R.string.label_followed);
            btn_follow.setBackgroundColor(getResources().getColor(R.color.greyText));
        } else {
            btn_follow.setText(R.string.label_follow);
            btn_follow.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void onFollow() {
        String url = "/user/relationship/update";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("fansid", id + "")
                .setBodyParameter("model", String.valueOf(!followed))
                .asJsonObject()
                .setCallback((e1, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            followed = !followed;

                            showFollow();
                        } else {
                            Toast.makeText(UserActivity.this, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void onSendMsg() {
        Intent intent = new Intent(this, ChatActivity.class);

        intent.putExtra("userInfo", userInfo);

        startActivity(intent);
    }
}
