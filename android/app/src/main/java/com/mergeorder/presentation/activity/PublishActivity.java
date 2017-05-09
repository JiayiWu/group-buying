package com.mergeorder.presentation.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.bumptech.glide.Glide;
import com.koushikdutta.ion.Ion;
import com.mergeorder.R;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.view.AlterImageView;
import com.mergeorder.util.DpPxUtil;
import com.mergeorder.util.HttpUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 17-4-29.
 * <p>
 * 发布帖子界面
 */
public class PublishActivity extends UploadActivity {

    public static final int TOPIC_CAR = 0;

    public static final int TOPIC_COURSE = 1;

    public static final int TOPIC_POSTAL = 2;

    public static final int TOPIC_GROUP = 3;

    private static final int REQUEST_GALLERY = 0;

    public static final int UPLOAD_SUCCESS = 0;

    public static final int UPLOAD_CANCEL = 1;

    private static final int TITLE_RES[] = new int[]{
            R.string.label_car, R.string.label_course, R.string.label_postal, R.string.label_group
    };

    private static final String[] URL = new String[]{
            "/car/order/add",
            "/class/order/add",
            "/mail/order/add",
            "/buy/order/add"
    };

    /**
     * 拼课、拼邮、拼团 分类
     */
    private Spinner sp_type;

    /**
     * 拼车 方向
     */
    private EditText et_direction;

    private EditText et_title, et_content;

    /**
     * 最多可插入三张图片
     */
    private AlterImageView[] images;

    private ImageView iv_image;

    private TextView tv_location;

    /**
     * 记录位置
     */
    private String location;

    /**
     * 用户id, 用于上传图片时标识
     */
    private int id;

    /**
     * topic 类型
     */
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_publish);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(UPLOAD_CANCEL);

        super.onBackPressed();
    }

    private void init() {
        id = ((MyApplication) getApplication()).getId();
        type = getIntent().getIntExtra("type", TOPIC_CAR);

        if (id == -1) {
            throw new RuntimeException("用户id为空");
        }

        initUIComponents();
        addListeners();
    }

    private void initUIComponents() {
        initToolBar();
        initTypeSpinner();
        initLocation();

        et_direction = (EditText) findViewById(R.id.publish_direction);
        if (type == TOPIC_CAR) {
            et_direction.setVisibility(View.VISIBLE);
        }

        et_title = (EditText) findViewById(R.id.publish_title);
        et_content = (EditText) findViewById(R.id.publish_content);

        images = new AlterImageView[]{
                (AlterImageView) findViewById(R.id.publish_image_0),
                (AlterImageView) findViewById(R.id.publish_image_1),
                (AlterImageView) findViewById(R.id.publish_image_2)
        };

        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        int size = (screenWidth - DpPxUtil.dip2px(this, 32)) / 3;
        int margin = DpPxUtil.dip2px(this, 8.0f);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        layoutParams.setMargins(0, 0, margin, 0);
        for (ImageView image : images) {
            image.setLayoutParams(layoutParams);
        }

        iv_image = (ImageView) findViewById(R.id.publish_add_picture);
    }

    private void addListeners() {
        iv_image.setOnClickListener(e -> addImage());
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.publish_toolbar);
        toolbar.setTitle(TITLE_RES[type]);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(e -> onBackPressed());

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.publish_publish:
                    onPublish();
                    break;
                default:
                    break;
            }

            return true;
        });
    }

    /**
     * 初始化分类 spinner
     */
    private void initTypeSpinner() {
        sp_type = (Spinner) findViewById(R.id.publish_type);

        if (type == TOPIC_CAR) {
            sp_type.setVisibility(View.GONE);
            return;
        }

        ArrayAdapter<CharSequence> adapter;
        switch (type) {
            case TOPIC_COURSE:
                adapter = ArrayAdapter.createFromResource(this, R.array.course_type, android.R.layout.simple_spinner_item);
                break;
            case TOPIC_POSTAL:
                adapter = ArrayAdapter.createFromResource(this, R.array.postal_type, android.R.layout.simple_spinner_item);
                break;
            case TOPIC_GROUP:
                adapter = ArrayAdapter.createFromResource(this, R.array.group_type, android.R.layout.simple_spinner_item);
                break;
            default:
                return;
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_type.setAdapter(adapter);
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        tv_location = (TextView) findViewById(R.id.publish_location);

        AMapLocationClient mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.startLocation();
        mLocationClient.setLocationOption(new AMapLocationClientOption().setInterval(1000 * 60));
        mLocationClient.setLocationListener(aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    location = aMapLocation.getCity() + "•" + aMapLocation.getStreet();

                    tv_location.setText(location);
                }
            } else {
                Toast.makeText(this, "定位错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addImage() {
        FunctionConfig config = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(3)
                .build();

        GalleryFinal.openGalleryMuti(REQUEST_GALLERY, config, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int requestCode, List<PhotoInfo> resultList) {
                for (int i = 0; i < resultList.size(); i++) {
                    String path = resultList.get(i).getPhotoPath();

                    Glide.with(PublishActivity.this)
                            .load("file://" + path)
                            .centerCrop()
                            .into(images[i]);

                    images[i].setPath(path);
                    images[i].setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
            }
        });

    }

    private void onPublish() {
        if (type != TOPIC_CAR && sp_type.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "请选择分类", Toast.LENGTH_LONG).show();
            return;
        }

        if (type == TOPIC_CAR && "".equals(et_direction.getText().toString().trim())) {
            Toast.makeText(this, "方向不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        if ("".equals(et_title.getText().toString().trim())) {
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        long timestamp = new Date().getTime();

        for (int i = 0; i < images.length; i++) {
            images[i].uploadToServer(id + "_" + timestamp + "_" + i);
        }

        upload();
    }

    @Override
    public void upload() {
        if (!imageUploaded()) {
            return;
        }

        Ion.with(this)
                .load(HttpUtil.BASE_URL + URL[type])
                .setBodyParameter("title", et_title.getText().toString())
                .setBodyParameter("content", et_content.getText().toString())
                .setBodyParameter("location", location)
                .setBodyParameter("type", getType())
                .setBodyParameter("direction", et_direction.getText().toString())
                .setBodyParameter("imgUrl", getImageUrl())
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        if (result.get("result").getAsBoolean()) {
                            onUploadSuccess();
                        } else {
                            Toast.makeText(this, result.get("reason").getAsString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * 获取具体分类(吃喝、玩乐等)
     *
     * @return 返回分类编号
     * 每项编号可能代表不同分类，如：
     * 拼团： 0 - 吃喝， 1 - 玩乐
     * 拼邮： 0 - 日本， 1 - 韩国
     */
    private String getType() {
        // "请选择分类" 提示字样 占用了索引0
        return (sp_type.getSelectedItemPosition() + 1) + "";
    }

    /**
     * 判断所有图片是否上传结束
     */
    private boolean imageUploaded() {
        for (AlterImageView image : images) {
            if (!image.hasUploaded()) {
                return false;
            }
        }

        return true;
    }

    private String getImageUrl() {
        StringBuilder url = new StringBuilder();

        for (AlterImageView image : images) {
            if (image.getPath() != null) {
                url.append(image.getPath());
            }
        }

        return url.toString();
    }

    private void onUploadSuccess() {
        setResult(UPLOAD_SUCCESS);

        finish();
    }
}
