package com.mergeorder.presentation.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.*;
import android.widget.*;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.mergeorder.MainActivity;
import com.mergeorder.R;
import com.mergeorder.model.Advertisement;
import com.mergeorder.model.Topic;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.activity.DiscoverTypeActivity;
import com.mergeorder.presentation.activity.PublishActivity;
import com.mergeorder.presentation.view.TopicListView;
import com.mergeorder.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 17-4-23.
 * <p>
 * “首页”
 */
public class IndexFragment extends Fragment {

    public static final int REQUEST_PUBLISH = 0;

    private View view;

    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * topic列表头，包含广告
     */
    private LinearLayout advertiseContainer;

    /**
     * topic列表头，包含分类
     */
    private LinearLayout typeContainer;

    private ImageButton[] btn_type;

    private TopicListView lv_topicList;

    private FloatingActionButton fab_course, fab_postal, fab_car, fab_group;

    private List<Advertisement> advertisementList;

    private List<Topic> topicList;

    private static final String[] TITLE = new String[]{
            "拼课", "拼邮", "拼车", "拼团"
    };

    private static final String[] TYPE_URL = new String[]{
            "/class/type/list",
            "/mail/type/list",
            "/car/sort/list",
            "/buy/type/list"
    };

    private static final String[][] TYPE_NAME = new String[][]{
            new String[]{"雅思托福", "BEC托业", "考研", "考证", "小语种", "考驾照", "来健身", "其他"},
            new String[]{"日本", "韩国", "美国", "欧洲", "澳洲", "港澳台泰", "其他地区", "网店拼邮"},
            new String[]{"大连站", "大连北站", "机场方向", "旅顺方向", "大连港", "金石滩", "开发区", "更多"},
            new String[]{"吃喝", "玩乐", "服饰", "其他"}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);

        advertiseContainer = (LinearLayout) inflater.inflate(R.layout.list_index_header_advertise, container, false);

        typeContainer = (LinearLayout) inflater.inflate(R.layout.list_index_header_type, container, false);

        init();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PUBLISH && resultCode == PublishActivity.UPLOAD_SUCCESS) {
            ((MainActivity) getActivity()).infoShouldUpdate = true;
        }
    }

    private void init() {
        initUIComponents();
        addListeners();
    }

    private void initUIComponents() {
        initSwipeRefresh();
        initAdvertiseBanner();
        initTopicList();

        btn_type = new ImageButton[]{
                (ImageButton) typeContainer.findViewById(R.id.btn_index_course),
                (ImageButton) typeContainer.findViewById(R.id.btn_index_postal),
                (ImageButton) typeContainer.findViewById(R.id.btn_index_car),
                (ImageButton) typeContainer.findViewById(R.id.btn_index_group)
        };

        FloatingActionMenu floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.index_publish_menu);
        floatingActionMenu.setClosedOnTouchOutside(true);

        fab_course = (FloatingActionButton) view.findViewById(R.id.index_btn_publish_course);
        fab_postal = (FloatingActionButton) view.findViewById(R.id.index_btn_publish_postal);
        fab_car = (FloatingActionButton) view.findViewById(R.id.index_btn_publish_car);
        fab_group = (FloatingActionButton) view.findViewById(R.id.index_btn_publish_group);
    }

    /**
     * 设置下拉刷新
     */
    private void initSwipeRefresh() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.index_swipe_refresh);

        // 下拉刷新topic list
        swipeRefreshLayout.setOnRefreshListener(this::initTopicList);
    }

    private void initAdvertiseBanner() {
        String url = "/advertisement/list";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        advertisementList = new Gson().fromJson(
                                result.get("object"), new TypeToken<List<Advertisement>>() {
                                }.getType());

                        showAdvertise();
                    }
                });
    }

    private void showAdvertise() {
        BGABanner advertiseBanner = (BGABanner) advertiseContainer.findViewById(R.id.advertisement_banner);
        advertiseBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(getActivity())
                        .load(model)
                        .placeholder(R.mipmap.advertisement)
                        .error(R.mipmap.advertisement)
                        .centerCrop()
                        .into(itemView);
            }
        });

        List<String> imgUrlList = new ArrayList<>();
        List<String> contentList = new ArrayList<>();

        for (Advertisement advertisement : advertisementList) {
            imgUrlList.add(advertisement.getImgURL());
            contentList.add(advertisement.getContents());
        }

        advertiseBanner.setData(imgUrlList, contentList);

        advertiseBanner.setDelegate((banner, itemView, model, position) -> {
            //从其他浏览器打开
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(advertisementList.get(position).getTargetURL());
            intent.setData(content_url);
            startActivity(Intent.createChooser(intent, "请选择浏览器"));
        });
    }

    private void addListeners() {
        for (int i = 0; i < btn_type.length; i++) {
            int finalI = i;

            btn_type[i].setOnClickListener(v -> openDiscoverItem(finalI));
        }

        fab_course.setOnClickListener(e -> openPublish(PublishActivity.TOPIC_COURSE));
        fab_postal.setOnClickListener(e -> openPublish(PublishActivity.TOPIC_POSTAL));
        fab_car.setOnClickListener(e -> openPublish(PublishActivity.TOPIC_CAR));
        fab_group.setOnClickListener(e -> openPublish(PublishActivity.TOPIC_GROUP));
    }

    private void initTopicList() {
        lv_topicList = (TopicListView) view.findViewById(R.id.index_topic_list);

        String url = "/index/recommend";

        Ion.with(this)
                .load(HttpUtil.BASE_URL + url)
                .asJsonObject()
                .setCallback((e, result) -> {
                    if (result != null) {
                        topicList = new Gson().fromJson(
                                result.get("object"), new TypeToken<List<Topic>>() {
                                }.getType());

                        lv_topicList.setData(topicList).show();

                        if (!swipeRefreshLayout.isRefreshing()) {
                            lv_topicList.addHeaderView(advertiseContainer);
                            lv_topicList.addHeaderView(typeContainer);
                        }

                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    /**
     * 打开精选中的指定项目
     *
     * @param index 项目索引，fragment_discover_tab.xml对应于 DiscoverFragment 中的ViewPager
     */
    private void openDiscoverItem(int index) {
        Intent intent = new Intent(getContext(), DiscoverTypeActivity.class);

        intent.putExtra("title", TITLE[index]);
        intent.putExtra("url", TYPE_URL[index]);
        intent.putExtra("type", TYPE_NAME[index]);

        startActivity(intent);
    }

    private void openPublish(int type) {
        if (!((MyApplication) getContext().getApplicationContext()).isLogin()) {
            ((MainActivity) getActivity()).showFragmentTab(MainActivity.LOGIN_TAB);

            return;
        }

        Intent intent = new Intent(getContext(), PublishActivity.class);

        intent.putExtra("type", type);

        startActivityForResult(intent, REQUEST_PUBLISH);
    }
}
