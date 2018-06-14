package com.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ActivityBase implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                Intent intent = new Intent(this, FragmentTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
