package com.peak.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.peak.recycler.holder.BaseHolder;

import java.util.List;

public abstract class BaseAdapter<Data> extends RecyclerView.Adapter<BaseHolder> {

    private final List<Data> mDataList;

    public BaseAdapter(List<Data> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override

    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder baseHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
