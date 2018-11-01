package com.peak.recycler.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseAdapter<Data> extends RecyclerView.Adapter<BaseHolder> {

    private final ArrayList<Data> mDataList;
    private Context mContext;
    private LayoutInflater mInflater;

    public BaseAdapter() {
        mDataList = new ArrayList<>();
    }

    public BaseAdapter(ArrayList<Data> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public final BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
            mInflater = LayoutInflater.from(mContext);
        }

        BaseHolder holder = createBaseHolder(mInflater, parent, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder baseHolder, int position) {
        bindViewData(baseHolder, position, getData(position), getItemViewType(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    protected abstract BaseHolder createBaseHolder(LayoutInflater inflater, ViewGroup parent, int viewType);

    protected abstract void bindViewData(BaseHolder baseHolder, int position, Data data, int viewType);

    @Override
    public abstract int getItemViewType(int position);

    //数据操作
    public void add(Data... datas) {
        if (datas != null && Collections.addAll(mDataList, datas)) {
            notifyItemRangeInserted(mDataList.size() - datas.length, datas.length);
        }
    }

    public void addByIndex(int index, Data data) {
        mDataList.add(index, data);
        notifyItemInserted(index);
    }

    public void add(Collection<? extends Data> items) {
        if (mDataList == items) {
            //noinspection unchecked
            items = (Collection<? extends Data>) mDataList.clone();
        }
        if (items != null && mDataList.addAll(items)) {
            notifyItemRangeInserted(mDataList.size() - items.size(), items.size());
        }
    }

    public void remove(Data data) {
        int idx = indexOf(data);
        if (idx >= 0) {
            removeAt(idx);
        }
    }

    public void removeAt(int... positions) {
        int len = positions != null ? positions.length : 0;
        if (len > 0) {
            Arrays.sort(positions);
            for (int i = positions.length - 1; i >= 0; i--) {
                int pos = positions[i];
                mDataList.remove(pos);
                notifyItemRemoved(pos);
            }
            notifyItemRangeChanged(positions[0], getItemCount() - positions[0]);
        }
    }

    public void setData(int position, Data data) {
        mDataList.set(position, data);
        notifyItemChanged(position);
    }

    public void insert(int position, Data data) {
        mDataList.add(position, data);
        notifyItemInserted(position);
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    public boolean refresh(Data data) {
        int pos = mDataList.indexOf(data);
        if (pos >= 0) {
            mDataList.set(pos, data);
            notifyItemChanged(pos);
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public Data getData(int position) {
        return mDataList.get(position);
    }

    public int indexOf(Data data) {
        return mDataList.indexOf(data);
    }

}
