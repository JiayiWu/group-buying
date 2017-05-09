package com.mergeorder.presentation.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by song on 17-5-2.
 * <p>
 * 拥有上传图片操作的activity的父类
 */
public abstract class UploadActivity extends AppCompatActivity {

    /**
     * 图片上传结束后,调用此方法将修改提交至服务器
     */
    public abstract void upload();
}
