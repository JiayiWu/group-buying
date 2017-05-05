package com.fivedreamer.utils;

import okhttp3.*;

import java.util.logging.Logger;

/**
 * Created by Jiayiwu on 17/5/5.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class OkHttpUtil {
    private static OkHttpUtil OkHttpUtil = new OkHttpUtil();
    private static OkHttpClient client;

    public static OkHttpUtil getInstance() {
        client = new OkHttpClient().newBuilder().build();
        return OkHttpUtil;
    }
    public Boolean postJson(String target, String json) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, json);
        long timestamp = System.currentTimeMillis();
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(target)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                return true;
            } else {
              return  false;
            }
        } catch (Exception e) {
            e.printStackTrace();
           return false;
        }
    }

}
