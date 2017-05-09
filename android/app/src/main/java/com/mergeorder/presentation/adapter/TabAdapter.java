package com.mergeorder.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import cn.finalteam.toolsfinal.adapter.FragmentAdapter;

import java.util.List;

/**
 * Created by song on 17-5-4.
 *
 * 搜索结果页
 */
public class TabAdapter extends FragmentAdapter {

    private List<Fragment> fragmentList;

    private List<String> nameList;

    public TabAdapter(FragmentManager fm, List<Fragment> list, List<String> tabList) {
        super(fm, list, tabList);

        this.fragmentList = list;
        this.nameList = tabList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return nameList.get(position);
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
}
