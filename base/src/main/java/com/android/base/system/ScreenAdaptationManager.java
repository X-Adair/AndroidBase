package com.android.base.system;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * 屏幕适配解决方案，采用今日头条适配方案
 * created at 2018/6/28 10:22
 *
 * @author XuShuai
 * @version v1.0
 */
public class ScreenAdaptationManager {

    private static float sNonCompatDensity;
    private static float sNonCompatScaledDensity;

    /**
     * 在Activity onCreated(方法中调用此方法)
     *
     * @param activity    当前Activity页面
     * @param application Application对象
     * @param density     设计图的宽度，单位dp
     */
    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application, float density, boolean isHeight) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNonCompatDensity == 0) {
            sNonCompatDensity = appDisplayMetrics.density;
            sNonCompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        final float targetDensity;
        if (isHeight) {
            targetDensity = appDisplayMetrics.widthPixels / density;
        } else {
            targetDensity = appDisplayMetrics.heightPixels / density;
        }
        final float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
