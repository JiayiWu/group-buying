package com.mergeorder.util;

/**
 * Created by song on 17-5-3.
 *
 * topic type 工具类
 */
public class TypeUtil {

    private static String[] mapping = new String[]{"拼车", "拼课", "拼邮", "拼团"};

    private TypeUtil() {
        /*do nothing*/
    }

    public static String getType(int topicType, String type) {
        return mapping[topicType] + " • " + type;
    }
}
