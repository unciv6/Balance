package com.peak.balance.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peak.balance.db.bean.Expend;
import com.peak.recycler.base.BaseHolder;
import com.peak.recycler.strategy.StrategyAdapter;

public class ExpendStrategy extends StrategyAdapter.AbsStrategy<Expend> {
    @Override
    public BaseHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new BaseHolder(view);
    }

    @Override
    public void bindData(BaseHolder holder, Expend data, int position) {
        TextView textView = (TextView) holder.getViewById(android.R.id.text1);

        textView.setText(data.toString());
    }

    @Override
    public boolean canHandle(Object obj) {
        return obj instanceof Expend;
    }
}
