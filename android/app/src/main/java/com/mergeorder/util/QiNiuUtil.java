package com.mergeorder.util;

import android.app.Activity;
import com.koushikdutta.ion.Ion;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by song on 17-5-1.
 * <p>
 * 七牛文件工具类
 */
public abstract class QiNiuUtil {

    public static UploadManager uploadManager;

    public static final String DOMIN = "http://op8aopzaq.bkt.clouddn.com/";

    private static String TOKEN;

    static {
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .zone(Zone.httpAutoZone) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();

        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
    }

    public static void getToken(Activity activity) {
        String url = "/auth/token";

        Ion.with(activity)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        TOKEN = result.get("object").getAsString();
                    }
                });
    }

    public void upload(String filePath, String key) {
        File file = new File(filePath);

        uploadManager.put(file, key, TOKEN, this::onComplete, null);
    }

    public abstract void onComplete(String key, ResponseInfo info, JSONObject res);
}
