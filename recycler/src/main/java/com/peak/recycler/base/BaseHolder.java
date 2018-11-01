package com.peak.recycler.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class BaseHolder<T> extends RecyclerView.ViewHolder {

    private final View mView;
    private final SparseArray<View> mViewSparseArray = new SparseArray<>();

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

    public <V extends View> V getView() {
        return (V) mView;
    }


    public <V extends View> V getViewById(int id) {
        View view = mViewSparseArray.get(id);
        if (view == null) {
            view = mView.findViewById(id);
            mViewSparseArray.put(id, view);
        }
        return (V) view;
    }

}
