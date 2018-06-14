package com.android.base;

import android.app.Application;

import com.android.base.system.CrashHandler;

/**
 * created at 2018/6/13 10:06
 *
 * @author XuShuai
 * @version v1.0
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this, "");
    }
}
