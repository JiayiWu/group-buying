package com.mergeorder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import cn.finalteam.galleryfinal.*;
import com.hyphenate.chat.EMClient;
import com.mergeorder.controller.LoginController;
import com.mergeorder.presentation.MyApplication;
import com.mergeorder.presentation.adapter.FragmentTabAdapter;
import com.mergeorder.presentation.fragment.*;
import com.mergeorder.util.GlideImageLoader;
import com.mergeorder.util.QiNiuUtil;

/**
 * Created by song on 17-4-23.
 * <p>
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    public static final int LOGIN_TAB = 0;

    public static final int REGISTER_TAB = 1;

    private Toolbar toolbar;

    /**
     * "首页" tab
     */
    private RadioButton indexTab;

    private LoginController loginController;

    private FragmentTabAdapter fragmentTabAdapter;

    /**
     * fragment tab切换前的tabId
     * 方便切换回到上个tab
     */
    private int previousTab;

    /**
     * 标记是否需要更新 MeFragment，
     * 如发布、评论 topic后更新界面
     */
    public boolean infoShouldUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();
    }

    @Override
    public void onBackPressed() {
        if (indexTab.isChecked()) { // "首页" 直接返回(退出)
            super.onBackPressed();
        } else { // 其它界面跳转至 "首页"
            indexTab.setChecked(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 显示之前的tab
        showPrevious();
    }

    private void init() {
        loginController = new LoginController(this);

        initQiniu();
        initGallerFinal();
    }

    /**
     * 初始化七牛
     */
    private void initQiniu() {
        QiNiuUtil.getToken(this);
    }

    /**
     * 初始化相册
     */
    private void initGallerFinal() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.colorPrimary))
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    /**
     * 初始化UI组件
     */
    private void initView() {
        indexTab = (RadioButton) findViewById(R.id.index_tab);

        initToolbar();
        initBottomTabs();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnClickListener(v -> onSearchRequested());

//        SearchView searchView = (SearchView) findViewById(R.id.index_search_view);

//        TextView hint = (TextView) searchView.findViewById(R.id.index_search_hint);
//
//        ImageView btn_search = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            btn_search.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
//        }
//
//        btn_search.setOnClickListener(v -> hint.setVisibility(View.GONE));
//
//        View btn_close = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
//        btn_close.setOnClickListener(v -> hint.setVisibility(View.VISIBLE));
    }

    /**
     * 初始化底部导航tab
     */
    private void initBottomTabs() {
        previousTab = R.id.index_tab;

        RadioGroup bottomTabs = (RadioGroup) findViewById(R.id.bottom_tabs);

        SparseArray<Fragment> fragmentMap = new SparseArray<>();

        fragmentMap.put(R.id.index_tab, new IndexFragment());
        fragmentMap.put(R.id.discover_tab, new DiscoverFragment());
        fragmentMap.put(R.id.chat_tab, new ChatFragment());
        fragmentMap.put(R.id.me_tab, new MeFragment());
        fragmentMap.put(0, new LoginFragment());
        fragmentMap.put(1, new RegisterFragment());

        fragmentTabAdapter = new BottomTabBar(this, fragmentMap, R.id.content, bottomTabs);
    }

    public LoginController getLoginController() {
        return loginController;
    }

    /**
     * 显示指定fragment tab
     *
     * @param tabId 目标tab 对应的 RadioButton id
     */
    public void showFragmentTab(int tabId) {
        fragmentTabAdapter.setFragmentTab(tabId);
    }

    /**
     * 显示前一个fragment tab
     * <p>
     * 用于登录成功后切换
     */
    public void showPrevious() {
        showFragmentTab(previousTab);
    }

    private class BottomTabBar extends FragmentTabAdapter {

        BottomTabBar(FragmentActivity fragmentActivity, SparseArray<Fragment> fragmentMap, int fragmentContentId, RadioGroup radioGroup) {
            super(fragmentActivity, fragmentMap, fragmentContentId, radioGroup);
        }

        @Override
        public boolean onTabWillChange(int tabId) {
            // 未登录访问 "chat"、"me"时，跳转至"login"
            if (!((MyApplication) getApplication()).isLogin()) {
                if (tabId == R.id.chat_tab || tabId == R.id.me_tab) {

                    fragmentTabAdapter.setFragmentTab(0);
                    return false;
                }
            }

            // 首页显示工具栏,其他界面隐藏
            if (tabId == R.id.index_tab) {
                toolbar.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
            }

            if (tabId != LOGIN_TAB && tabId != REGISTER_TAB) {
                previousTab = tabId;
            }

            return true;
        }

        @Override
        public void onTabChanged(int tabId) {
            /*do nothing for this time*/
        }
    }
}
