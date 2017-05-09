package com.mergeorder.util;

/**
 * Created by song on 17-5-1.
 * <p>
 * 电话号工具类
 */
public class PhoneUtil {

    private static final String PHONE_REGEX = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";

    private PhoneUtil() {
        /*do nothing*/
    }

    /**
     * 判断电话号是否合法
     */
    public static boolean isLegal(String phoneNum) {
        return phoneNum.matches(PHONE_REGEX);
    }
}
