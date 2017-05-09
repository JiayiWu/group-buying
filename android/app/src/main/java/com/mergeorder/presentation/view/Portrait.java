package com.mergeorder.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import com.bumptech.glide.Glide;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.activity.UserActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by song on 17-5-3.
 *
 * 用户头像，点击跳转至用户信息界面
 */
public class Portrait extends CircleImageView {

    /**
     * 用户id
     */
    private int id;

    private String url;

    public Portrait(Context context) {
        super(context);

        init();
    }

    public Portrait(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public Portrait(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        setOnClickListener(v -> {
            int userId = ((MyApplication) getContext().getApplicationContext()).getId();

            if (userId == id) {
                return;
            }

            Intent intent = new Intent(getContext(), UserActivity.class);

            intent.putExtra("id", id);

            getContext().startActivity(intent);
        });
    }

    public Portrait setUserId(int id) {
        this.id = id;

        return this;
    }

    public Portrait setUrl(String url) {
        this.url = url;

        return this;
    }

    public void show() {
        Glide.with(getContext())
                .load(Uri.parse(url))
                .into(this);
    }
}
