package com.mergeorder.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import com.bumptech.glide.Glide;
import com.mergeorder.presentation.activity.UploadActivity;
import com.mergeorder.util.QiNiuUtil;
import com.qiniu.android.http.ResponseInfo;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by song on 17-5-2.
 * <p>
 * 可使用相册替换的 ImageView
 */
public class AlterImageView extends ImageView {

    /**
     * 记录是否上传成功
     * 修改path时,设为false
     * 上传成功后设为true
     */
    private boolean upload = true;

    private String path;

    public AlterImageView(Context context) {
        super(context);

        addListeners();
    }

    public AlterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        addListeners();
    }

    public AlterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        addListeners();
    }

    private void addListeners() {
        setOnClickListener(v -> GalleryFinal.openGallerySingle(0, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {
                path = resultList.get(0).getPhotoPath();

                Glide.with(getContext())
                        .load("file://" + path)
                        .centerCrop()
                        .into(AlterImageView.this);
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        }));
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        upload = false;
    }

    public boolean hasUploaded() {
        return upload;
    }

    /**
     * 上传至七牛
     *
     * @param key 目标文件名
     */
    public void uploadToServer(String key) {
        if (upload) {
            return;
        }

        new QiNiuUtil() {
            @Override
            public void onComplete(String key, ResponseInfo info, JSONObject res) {
                if (info.isOK()) {
                    upload = true;

                    ((UploadActivity) getContext()).upload();
                } else {
                    Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            }
        }.upload(getPath(), key);
    }
}
