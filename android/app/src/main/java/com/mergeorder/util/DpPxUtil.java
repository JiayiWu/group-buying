package com.mergeorder.util;

import android.content.Context;

/**
 * Created by song on 17-5-2.
 *
 * dp px 转换工具类
 */
public class DpPxUtil {

    private DpPxUtil() {
        /*do nothing*/
    }

    /**
     * dp2px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px2dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }
}
