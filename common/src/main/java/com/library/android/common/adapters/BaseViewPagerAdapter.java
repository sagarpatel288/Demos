package com.library.android.common.adapters;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * 11/10/2019
 * Get a viewPager adapter that handles fragment caching, dynamic changes and configuration changes and returns
 * the working fragment
 * @author srdpatel
 * @since $1.0$
 */
public class BaseViewPagerAdapter extends SmartFragmentStatePagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();

    public BaseViewPagerAdapter(ViewGroup pagerInstance, FragmentManager fragmentManager) {
        super(pagerInstance, fragmentManager);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mTitleList.add(title);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        // com: 11/10/2019 manage configuration change as the variable can become null in that case
        return mFragmentList.size();
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
