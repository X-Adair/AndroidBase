package com.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * created at 2018/6/13 17:43
 *
 * @author XuShuai
 * @version v1.0
 */
public class HideShowFragmentActivity extends ActivityBase implements View.OnClickListener {

    private FragmentLazyOne fragmentLazyOne;
    private FragmentLazyTwo fragmentLazyTwo;
    private FragmentLazyThree fragmentLazyThree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_show_fragment);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);

        fragmentLazyOne = FragmentLazyOne.newInstance("参数1", "参数2");
        fragmentLazyTwo = FragmentLazyTwo.newInstance();
        fragmentLazyThree = FragmentLazyThree.newInstance();

        addFragment(getSupportFragmentManager());
        showFragment(fragmentLazyOne,getSupportFragmentManager());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button3:
                showFragment(fragmentLazyOne, getSupportFragmentManager());
                break;
            case R.id.button4:
                showFragment(fragmentLazyTwo, getSupportFragmentManager());
                break;
            case R.id.button5:
                showFragment(fragmentLazyThree, getSupportFragmentManager());
                break;
        }
    }

    private void addFragment(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, fragmentLazyOne, "fragmentLazyOne");
        transaction.add(R.id.content, fragmentLazyTwo, "fragmentLazyTwo");
        transaction.add(R.id.content, fragmentLazyThree, "fragmentLazyThree");
        transaction.hide(fragmentLazyOne).hide(fragmentLazyTwo).hide(fragmentLazyThree).commit();
    }

    private void showFragment(FragmentLazy fragmentLazy, FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentLazy instanceof FragmentLazyOne) {
            transaction.show(fragmentLazyOne).hide(fragmentLazyTwo).hide(fragmentLazyThree).commit();
        } else if (fragmentLazy instanceof FragmentLazyTwo) {
            transaction.show(fragmentLazyTwo).hide(fragmentLazyOne).hide(fragmentLazyThree).commit();
        } else {
            transaction.show(fragmentLazyThree).hide(fragmentLazyOne).hide(fragmentLazyTwo).commit();
        }
    }

}
