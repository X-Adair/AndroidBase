package com.android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2018/6/13 15:59
 *
 * @author XuShuai
 * @version v1.0
 */
public class FragmentLazyTwo extends FragmentLazy {

    private static final String TAG = "FragmentLazyTwo";

    private RecyclerView recyclerView;

    private List<String> dataList;

    private RecyclerViewAdapter adapter;

    public FragmentLazyTwo() {
    }

    public static FragmentLazyTwo newInstance() {
        return new FragmentLazyTwo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lazy_two, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        dataList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(dataList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void loadData() {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        dataList.clear();
        for (int i = 0; i < 200; i++) {
            dataList.add("Position:" + i);
        }
        adapter.setData(dataList);
        Log.d(TAG, "FragmentLazyTwo: Load数据");
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

        private List<String> mDataList;

        public RecyclerViewAdapter(List<String> data) {
            mDataList = data;
        }

        public void setData(List<String> mDataList) {
            this.mDataList = mDataList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.activity_list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(mDataList.get(position));
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        @Override
        public int getItemCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(android.R.id.icon);
                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }

}
