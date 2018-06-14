package com.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * created at 2018/6/13 15:45
 *
 * @author XuShuai
 * @version v1.0
 */
public class FragmentTestActivity extends ActivityBase implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button:
                Intent intent = new Intent(this, ViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(this, HideShowFragmentActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
