package com.peak.balance.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peak.recycler.base.BaseHolder;
import com.peak.recycler.strategy.StrategyAdapter;

public class TempStrategy extends StrategyAdapter.AbsStrategy<String> {
    @Override
    public BaseHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new BaseHolder(view);
    }

    @Override
    public void bindData(BaseHolder holder, String data, int position) {
        TextView textView = (TextView) holder.getViewById(android.R.id.text1);
        textView.setGravity(Gravity.CENTER);
        textView.setText(data);
    }

    @Override
    public void onClicked(View view, int position, String s) {

    }

    @Override
    public void onLongClicked(View view, int position, String s) {

    }

    @Override
    public boolean canHandle(Object obj) {
        return obj instanceof String;
    }

}
