package com.android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends ActivityBase {

    private ViewPager viewPager;

    private List<FragmentLazy> fragmentLazyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = findViewById(R.id.viewPage);

        fragmentLazyList = new ArrayList<>();
        fragmentLazyList.add(FragmentLazyOne.newInstance("参数1", "参数2"));
        fragmentLazyList.add(FragmentLazyTwo.newInstance());
        fragmentLazyList.add(FragmentLazyThree.newInstance());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentLazyList);
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<FragmentLazy> fragmentLazyList;

        public ViewPagerAdapter(FragmentManager fm, List<FragmentLazy> fragmentLazies) {
            super(fm);
            fragmentLazyList = fragmentLazies;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentLazyList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentLazyList.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }

}
