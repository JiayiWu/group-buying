package com.mergeorder.controller;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.koushikdutta.ion.Ion;
import com.mergeorder.MainActivity;
import com.mergeorder.model.User;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.util.HttpUtil;
import com.mergeorder.util.PhoneUtil;

/**
 * Created by song on 17-4-24.
 * <p>
 * 登录相关
 */
public class LoginController {

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    /**
     * 登录
     *
     * @param phoneNum 手机号
     * @param password 密码
     */
    public void login(String phoneNum, String password) {
        if (phoneNum.length() != 11 || !PhoneUtil.isLegal(phoneNum)) {
            Toast.makeText(context, "手机号错误", Toast.LENGTH_LONG).show();
            return;
        }

        String url = "/login";

        Ion.with(context)
                .load(HttpUtil.BASE_URL + url)
                .setBodyParameter("phoneNumber", phoneNum)
                .setBodyParameter("password", password)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            User user = new Gson().fromJson(result.get("object"), User.class);

                            ((MyApplication) context.getApplicationContext()).setId(user.getId());
                            ((MyApplication) context.getApplicationContext()).setUser(user);

                            ((MainActivity) context).showPrevious();

                            loginEM(phoneNum, password);
                        } else {
                            Toast.makeText(context, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, "请检查网络连接", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void loginEM(String phoneNum, String password) {
        if (EMClient.getInstance().isLoggedInBefore()) {
            return;
        }

        EMClient.getInstance().login(phoneNum, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().chatManager().loadAllConversations();
            }

            @Override
            public void onProgress(int progress, String status) {
                System.out.println("LoginController.onProgress");
            }

            @Override
            public void onError(int code, String message) {
                System.out.println(message);
                System.out.println("LoginController.onError");
            }
        });
    }
}
