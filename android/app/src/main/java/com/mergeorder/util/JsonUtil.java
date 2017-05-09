package com.mergeorder.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

/**
 * Created by song on 17-5-1.
 * <p>
 * json工具类
 */
public class JsonUtil {

    private JsonUtil() {
        /*do nothing*/
    }

    /**
     * 将 json 格式字符串转换为指定 VO 对象
     *
     * @param c    VO 对象的class
     * @param json json字符串
     * @param <T>  指定泛型
     * @return VO 对象
     */
    public static <T> T parseObject(Class<T> c, String json) throws JSONException, IllegalAccessException, InstantiationException {
        JSONObject jsonObject = new JSONObject(json);

        T instance = c.newInstance();
        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            field.set(instance, jsonObject.get(field.getName()));
        }

        return instance;
    }
}
