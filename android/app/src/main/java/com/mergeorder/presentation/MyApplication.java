package com.mergeorder.presentation;

import android.app.Application;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.mergeorder.model.User;

/**
 * Created by song on 17-5-3.
 * <p>
 * 存储全局对象
 */
public class MyApplication extends Application {

    /**
     * 用户id
     */
    private int id = -1;

    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogin() {
        return id != -1;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initEM();
    }

    /**
     * 初始化环信
     */
    private void initEM() {
        EMOptions options = new EMOptions();
//         默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//        options.setAutoLogin(false);

        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);

        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
