package com.android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * created at 2018/6/13 15:59
 *
 * @author XuShuai
 * @version v1.0
 */
public class FragmentLazyOne extends FragmentLazy {

    private static final String TAG = "FragmentLazyOne";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView text1;

    public FragmentLazyOne() {}

    public static FragmentLazyOne newInstance(String param1, String param2) {
        FragmentLazyOne fragmentLazyOne = new FragmentLazyOne();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        fragmentLazyOne.setArguments(bundle);
        return fragmentLazyOne;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lazy_one, container, false);
        text1 = view.findViewById(R.id.text1);
        text1.setText("FragmentLazyOne\n");
        text1.append(ARG_PARAM1 + " : " + mParam1 + "\n");
        text1.append(ARG_PARAM2 + " : " + mParam2);
        return view;
    }

    @Override
    public void loadData() {
        Log.d(TAG, "FragmentLazyOne: Load数据");
    }
}
