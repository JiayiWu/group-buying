package com.mergeorder.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mergeorder.util.TypeUtil;

/**
 * Created by song on 17-5-3.
 *
 * 分类 TextView
 *
 * 点击跳转至相应精选界面
 */
public class TypeTextView extends TextView {

    /**
     * topic分类(拼车、拼课等)
     */
    private int topicType;

    /**
     * 具体分类
     */
    private String type;

    public TypeTextView(Context context) {
        super(context);
    }

    public TypeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TypeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TypeTextView setTopicType(int topicType) {
        this.topicType = topicType;

        return this;
    }

    public TypeTextView setType(String type) {
        this.type = type;

        return this;
    }

    public void show() {
        setText(TypeUtil.getType(topicType, type));

        setOnClickListener(e -> {
            System.out.println(getText());
        });
    }
}
