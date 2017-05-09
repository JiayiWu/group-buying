package com.mergeorder.presentation.activity.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import com.bumptech.glide.Glide;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.model.UserInfo;
import com.mergeorder.util.HttpUtil;
import com.mergeorder.util.QiNiuUtil;
import com.qiniu.android.http.ResponseInfo;
import com.trncic.library.DottedProgressBar;
import de.hdodenhof.circleimageview.CircleImageView;
import org.json.JSONObject;

import java.util.List;

/**
 * 编辑个人信息
 */
public class EditInfoActivity extends AppCompatActivity {

    public static final int RESULT_CODE = 1;

    private Toolbar toolbar;

    private ImageView background;

    private CircleImageView portrait;

    private TextView tx_nickname, tx_school;

    private RadioButton rb_man, rb_woman;

    private UserInfo userInfo;

    private String portraitTmp, bgTmp;

    private RelativeLayout progressBarContainer;

    private DottedProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);

        return true;
    }

    private void init() {
        Intent intent = getIntent();

        userInfo = (UserInfo) intent.getSerializableExtra("userInfo");

        initUIComponents();
        injectData();
        addListeners();
    }

    private void initUIComponents() {
        initToolBar();

        progressBarContainer = (RelativeLayout) findViewById(R.id.edit_info_progress_bar_container);
        progressBar = (DottedProgressBar) findViewById(R.id.edit_info_progress_bar);

        toolbar = (Toolbar) findViewById(R.id.edit_info_toolbar);

        background = (ImageView) findViewById(R.id.edit_into_img_bg);
        portrait = (CircleImageView) findViewById(R.id.edit_info_portrait);

        tx_nickname = (TextView) findViewById(R.id.edit_info_et_nickname);
        tx_school = (TextView) findViewById(R.id.login_et_school);

        rb_man = (RadioButton) findViewById(R.id.edit_info_man);
        rb_woman = (RadioButton) findViewById(R.id.edit_info_woman);
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.edit_info_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_done:
                    onEditDone();
                    break;
                default:
                    break;
            }

            return true;
        });

        toolbar.setNavigationOnClickListener(e -> onBackPressed());
    }

    private void addListeners() {
        background.setOnClickListener(e ->
                GalleryFinal.openGallerySingle(0, new GalleryCallback(background)));

        portrait.setOnClickListener(e ->
                GalleryFinal.openGallerySingle(0, new GalleryCallback(portrait)));
    }

    private void injectData() {
        tx_nickname.setText(userInfo.getNickname());
        tx_school.setText(userInfo.getSchool());

        if ("男".equals(userInfo.getSex())) {
            rb_man.setChecked(true);
        } else {
            rb_woman.setChecked(true);
        }
        Glide.with(this)
                .load(Uri.parse(userInfo.getIconurl()))
                .centerCrop()
                .placeholder(R.mipmap.avatar)
                .crossFade()
                .into(portrait);
        Glide.with(this)
                .load(Uri.parse(userInfo.getBackgroundurl()))
                .centerCrop()
                .placeholder(R.mipmap.bg_me)
                .crossFade()
                .into(background);
    }

    private void onEditDone() {
        progressBarContainer.setVisibility(View.VISIBLE);
        progressBar.startProgress();

        uploadImg();
        getData();
        updateInfo();
    }

    private void updateInfo() {
        // 头像和背景图片上传未结束
        if (portraitTmp != null || bgTmp != null) {
            return;
        }

        String url = "/user/update/info";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("nickName", userInfo.getNickname())
                .setBodyParameter("school", userInfo.getSchool())
                .setBodyParameter("sex", userInfo.getSex())
                .setBodyParameter("iconUrl", userInfo.getIconurl())
                .setBodyParameter("backgroundUrl", userInfo.getBackgroundurl())
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        progressBar.stopProgress();
                        progressBarContainer.setVisibility(View.GONE);
                        exit();
                    } else {
                        Toast.makeText(this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void getData() {
        userInfo.setNickname(tx_nickname.getText().toString());
        userInfo.setSchool(tx_school.getText().toString());
        if (rb_man.isChecked()) {
            userInfo.setSex("男");
        } else {
            userInfo.setSex("女");
        }
    }

    private void uploadImg() {
        if (portraitTmp != null) {
            new QiNiuUtil() {
                @Override
                public void onComplete(String key, ResponseInfo info, JSONObject res) {
                    if (info.isOK()) {
                        userInfo.setIconurl(QiNiuUtil.DOMIN + "portrait_" + userInfo.getId());
                        portraitTmp = null;
                        updateInfo();
                    } else {
                        Toast.makeText(EditInfoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }.upload(portraitTmp, "portrait_" + userInfo.getId());
        }

        if (bgTmp != null) {
            new QiNiuUtil() {
                @Override
                public void onComplete(String key, ResponseInfo info, JSONObject res) {
                    if (info.isOK()) {
                        userInfo.setBackgroundurl(QiNiuUtil.DOMIN + "bg_" + userInfo.getId());
                        bgTmp = null;
                        updateInfo();
                    } else {
                        Toast.makeText(EditInfoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }.upload(bgTmp, "bg_" + userInfo.getId());
        }
    }

    private void exit() {
        Intent intent = new Intent();

        intent.putExtra("userInfo", userInfo);

        setResult(RESULT_CODE, getIntent());
        finish();
    }

    private class GalleryCallback implements GalleryFinal.OnHanlderResultCallback {

        private ImageView target;

        private GalleryCallback(ImageView target) {
            this.target = target;
        }

        @Override
        public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {
            String path = resultList.get(0).getPhotoPath();

            if (target == portrait) {
                portraitTmp = path;
            } else if (target == background) {
                bgTmp = path;
            }

            Glide.with(EditInfoActivity.this)
                    .load("file://" + path)
                    .into(target);
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
        }
    }
}
