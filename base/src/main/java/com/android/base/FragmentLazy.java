package com.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * 懒加载Fragment,用于使用Hide和Show方法控制Fragment显示
 * 使用此Fragment，当Fragment第一次显示时再加载或请求数据
 * <p>
 * created at 2018/6/13 15:00
 *
 * @author XuShuai
 * @version v1.0
 */
public abstract class FragmentLazy extends Fragment {

    /**
     * Fragment是否是第一次加载数据
     */
    private boolean isFirstLoad = true;

    /**
     * Fragment View是否初始化完成标志位
     */
    private boolean isPrepared = false;

    /**
     * Fragment是否可见标志位
     */
    private boolean isVisible = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    /**
     * 当在ViewPager中Fragment被用户可见时，系统调用此方法并传入true
     * 此Fragment不被用户可见时调用此方法并传入false
     *
     * @param isVisibleToUser 是否被用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    /**
     * 当使用Hide()方法和Show()方法控制Fragment是否显示时，此方法被调用
     * <p>
     * 当Fragment第一个被加载显示时，需要先调用Hide()方法再调用Show()方法来触发此方法
     *
     * @param hidden 是否隐藏
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    /**
     * fragment可见时，调用此方法
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * fragment不可见时，调用此方法
     */
    protected void onInVisible() {

    }

    /**
     * 延迟加载控制方法
     */
    protected void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isPrepared) {
            return;
        }
        loadData();
        isFirstLoad = false;
    }

    /**
     * 此方法中实现Fragment请求数据,则只有Fragment在显示状态才会请求
     * <p>
     * created at 2018/6/13 15:05
     */
    public abstract void loadData();
}
