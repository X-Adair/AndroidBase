package com.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.base.system.ActivityStack;

/**
 * Activity基类,添加Activity进入stack栈
 *
 * @author XuShuai
 * @version v1.0
 */
public class ActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityStack.removeActivity(this);
        super.onDestroy();
    }
}
