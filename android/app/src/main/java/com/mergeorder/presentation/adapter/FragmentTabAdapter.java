package com.mergeorder.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.mergeorder.R;

public abstract class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {

    /**
     * 所有可能用到的 fragment 列表
     * Key: 界面对应的RadioButton id
     * Value: 对应界面
     */
    private SparseArray<Fragment> fragmentMap;

    private RadioGroup radioGroup;

    private FragmentActivity fragmentActivity;

    /**
     * 容器组件id
     */
    private int fragmentContentId;

    /**
     * 当前tab 对应的 RadioButton id
     */
    private int currentTab;

//    /**
//     * RadioButton name 与 fragment tab name 的映射
//     */
//    private static final Map<String, String> nameMapping;
//
//    static {
//        nameMapping = new HashMap<>();
//
//        nameMapping.put("首页", "index");
//        nameMapping.put("精选", "discover");
//        nameMapping.put("发布", "publish");
//        nameMapping.put("私聊", "chat");
//        nameMapping.put("我的", "me");
//    }

    protected FragmentTabAdapter(FragmentActivity fragmentActivity,
                                 SparseArray<Fragment> fragmentMap, int fragmentContentId, RadioGroup radioGroup) {
        this.fragmentMap = fragmentMap;
        this.radioGroup = radioGroup;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;

        this.init();
    }

    private void init() {
        // 初始状态装载 IndexFragment
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragmentMap.get(R.id.index_tab));
        ft.commit();

        radioGroup.setOnCheckedChangeListener(this);

        currentTab = R.id.index_tab;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//        String btnName = ((RadioButton) fragmentActivity.findViewById(checkedId)).getText().toString();
//        String tabName = nameMapping.get(btnName);

        // 切换
        changeTab(checkedId);
    }

    /**
     * 切换 fragment tab
     * <p>
     * 切换前调用 #onTabWillChange
     * 切换后调用 #onTabChanged
     *
     * @param tabId 目标 tab 对应的 RadioButton id
     */
    private void changeTab(int tabId) {
        if (tabId == currentTab) { // 选中当前tab无需切换
            return;
        }

        // 调用注册的回调函数
        if (onTabWillChange(tabId)) {
            // 显示选中的tab
            showTab(tabId);
            // 调用注册的回调函数
            onTabChanged(tabId);
        }
    }

    /**
     * 显示 fragment tab
     *
     * @param tabId 目标tab 对应的 RadioButton id
     */
    private void showTab(int tabId) {
        // 当前 fragment
        Fragment temp = getCurrentFragment();
        // 目标 fragment
        Fragment fragment = fragmentMap.get(tabId);

        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();

        if (fragment.isAdded()) { // 已添加
            fragment.onResume();
        } else {
            ft.add(fragmentContentId, fragment);
        }
        // 暂停
        temp.onPause();
        // 显示目标 fragment
        ft.show(fragmentMap.get(tabId));
        // 隐藏原 fragment
        ft.hide(temp);

        ft.commit();

        // 修改当前 fragment tab
        currentTab = tabId;
    }

    /**
     * 获取当前tab
     */
    private Fragment getCurrentFragment() {
        return fragmentMap.get(currentTab);
    }

    /**
     * 手动切换 fragment tab
     *
     * @param tabId 目标 tab RadioButton id
     */
    public void setFragmentTab(int tabId) {
        changeTab(tabId);

        if (fragmentActivity.findViewById(tabId) instanceof RadioButton) {
            ((RadioButton) fragmentActivity.findViewById(tabId)).setChecked(true);
        }
    }

    /**
     * fragment tab 切换前的回调函数
     *
     * @param tabId 目标tab 对应的 RadioButton id
     * @return 确定切换返回true，否则返回false
     */
    public abstract boolean onTabWillChange(int tabId);

    /**
     * fragment tab切换后的回调函数
     *
     * @param tabId 目标tab 对应的 RadioButton id
     */
    public abstract void onTabChanged(int tabId);
}
