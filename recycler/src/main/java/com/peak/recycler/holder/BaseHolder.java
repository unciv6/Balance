package com.peak.recycler.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {


    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(T data, int position);
}
