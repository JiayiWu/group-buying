package com.mergeorder.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by song on 17-5-3.
 *
 * 自定义ImageView，实现自动从网络加载
 */
public class MyImageView extends ImageView {

    private String url;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyImageView setUrl(String url) {
        this.url = url;

        return this;
    }

    public void show() {
        Glide.with(getContext())
                .load(Uri.parse(url))
                .centerCrop()
                .into(this);
    }
}
