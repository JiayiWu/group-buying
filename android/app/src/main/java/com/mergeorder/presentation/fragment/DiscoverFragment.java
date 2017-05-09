package com.mergeorder.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.mergeorder.R;
import com.mergeorder.presentation.adapter.TabAdapter;
import com.mergeorder.presentation.fragment.discover.DiscoverTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 17-4-23.
 * <p>
 * “精选”
 */
public class DiscoverFragment extends Fragment {

    private View view;

    private static final String[] NAME = new String[]{
            "拼课", "拼邮", "拼车", "拼团"
    };

    private static final String[] URL = new String[]{
            "/class/order/list",
            "/mail/order/list",
            "/car/order/list",
            "/buy/order/list"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_discover, container, false);

        initUIComponents();

        return view;
    }

    private void initUIComponents() {
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();

        for (int i = 0; i < NAME.length; i++) {
            DiscoverTabFragment fragment = new DiscoverTabFragment();

            Bundle bundle = new Bundle();
            bundle.putString("url", URL[i]);

            fragment.setArguments(bundle);

            fragmentList.add(fragment);
            nameList.add(NAME[i]);
        }

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.discover_viewpager);
        viewPager.setAdapter(new TabAdapter(getFragmentManager(), fragmentList, nameList));

        PagerSlidingTabStrip viewPagerTab = (PagerSlidingTabStrip) view.findViewById(R.id.discover_top_tabs);
        viewPagerTab.setViewPager(viewPager);
    }
}
