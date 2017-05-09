package com.mergeorder.presentation.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.ion.Ion;
import com.mergeorder.MainActivity;
import com.mergeorder.R;
import com.mergeorder.model.UserInfo;
import com.mergeorder.presentation.activity.me.*;
import com.mergeorder.util.HttpUtil;
import com.mergeorder.util.QiNiuUtil;
import com.qiniu.android.http.ResponseInfo;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by song on 17-4-23.
 * <p>
 * “我的”
 */
public class MeFragment extends Fragment {

    private static final int EDIT_CODE = 1;

    private View view;

    private MainActivity mainActivity;

    private ImageView iv_bg, iv_portrait, iv_verified;

    private Button btn_verify;

    private TextView tv_school, tv_sex, tv_successNum;

    private RelativeLayout con_fans, con_focus, con_publish, con_attend, con_comment;

    private TextView tv_fans_num, tv_focus_num, tv_publish_num, tv_attend_num, tv_comment_num;

    private FloatingActionButton btn_edit;

    private UserInfo userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_me, container, false);

        initUIComponents();
        addListeners();

        injectData();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mainActivity = ((MainActivity) getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mainActivity.infoShouldUpdate) {
            injectData();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_CODE && resultCode == EditInfoActivity.RESULT_CODE) {
            showData((UserInfo) data.getSerializableExtra("userInfo"));
        }
    }

    private void initUIComponents() {
        iv_bg = (ImageView) view.findViewById(R.id.me_img_bg);
        iv_portrait = (ImageView) view.findViewById(R.id.me_img_avatar);
        iv_verified = (ImageView) view.findViewById(R.id.user_verified);

        btn_verify = (Button) view.findViewById(R.id.verify);

        tv_school = (TextView) view.findViewById(R.id.me_school);
        tv_sex = (TextView) view.findViewById(R.id.me_sex);
        tv_successNum = (TextView) view.findViewById(R.id.me_success_num);

        con_fans = (RelativeLayout) view.findViewById(R.id.me_fans_container);
        con_focus = (RelativeLayout) view.findViewById(R.id.me_focus_container);
        con_publish = (RelativeLayout) view.findViewById(R.id.me_publish_container);
        con_attend = (RelativeLayout) view.findViewById(R.id.me_attend_container);
        con_comment = (RelativeLayout) view.findViewById(R.id.me_comment_container);

        tv_fans_num = (TextView) view.findViewById(R.id.me_fans_num);
        tv_focus_num = (TextView) view.findViewById(R.id.me_focus_num);
        tv_publish_num = (TextView) view.findViewById(R.id.me_publish_num);
        tv_attend_num = (TextView) view.findViewById(R.id.me_attend_num);
        tv_comment_num = (TextView) view.findViewById(R.id.me_comment_num);

        btn_edit = (FloatingActionButton) view.findViewById(R.id.me_btn_edit);
    }

    private void addListeners() {
        con_fans.setOnClickListener(e -> openNew(MyFansActivity.class));
        con_focus.setOnClickListener(e -> openNew(MyFollowActivity.class));
        con_publish.setOnClickListener(e -> openNew(MyPublishActivity.class));
        con_attend.setOnClickListener(e -> openNew(MyAttendActivity.class));
        con_comment.setOnClickListener(e -> openNew(MyCommentActivity.class));

        btn_verify.setOnClickListener(e -> GalleryFinal.openGallerySingle(0, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {
                Toast.makeText(getContext(), "照片上传中", Toast.LENGTH_LONG).show();

                btn_verify.setVisibility(View.GONE);

                String filePath = resultList.get(0).getPhotoPath();

                uploadStudentCard(filePath);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));

        btn_edit.setOnClickListener(e -> {
            Intent intent = new Intent(mainActivity, EditInfoActivity.class);

            intent.putExtra("userInfo", userInfo);

            startActivityForResult(intent, EDIT_CODE);
        });
    }

    private void openNew(Class c) {
        // TODO 是否更新界面
        Intent intent = new Intent(mainActivity, c);

        startActivity(intent);
    }

    private void injectData() {
        String url = "/user/info";

        Ion.with(mainActivity)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        handleResult(result);

                        btn_edit.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void handleResult(JsonObject result) {
        try {
            if (result.get("result").getAsBoolean()) {
                userInfo = new Gson().fromJson(result.get("object"), UserInfo.class);

                showData(userInfo);
            } else {
                Toast.makeText(mainActivity, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(mainActivity, "无网络连接", Toast.LENGTH_LONG).show();
        }
    }

    private void showData(UserInfo userInfo) {
        tv_school.setText(userInfo.getSchool());
        tv_sex.setText(userInfo.getSex());
        tv_successNum.setText(String.valueOf(userInfo.getSuccessCount()));

        tv_fans_num.setText(String.valueOf(userInfo.getFansCount()));
        tv_focus_num.setText(String.valueOf(userInfo.getFocusCount()));
        tv_publish_num.setText(String.valueOf(userInfo.getPostCount()));
        tv_attend_num.setText(String.valueOf(userInfo.getAttendCount()));
        tv_comment_num.setText(String.valueOf(userInfo.getCommentCount()));

        if (userInfo.isAuthentication()) {
            iv_verified.setVisibility(View.VISIBLE);
        } else {
            btn_verify.setVisibility(View.VISIBLE);
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
    }

    private void uploadStudentCard(String filePath) {
        String url = "/user/authentication/id";

        new QiNiuUtil() {
            @Override
            public void onComplete(String key, ResponseInfo info, JSONObject res) {
                if (info.isOK()) {
                    Ion.with(getContext())
                            .load(HttpUtil.BASE_URL + url)
                            .setBodyParameter("url", QiNiuUtil.DOMIN + "card_" + userInfo.getId())
                            .asJsonObject()
                            .setCallback((e, result) -> {
                                if (result.get("result").getAsBoolean()) {
                                    Toast.makeText(getContext(), "上传成功,请等待审核", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            }
        }.upload(filePath, "card_" + userInfo.getId());
    }
}
