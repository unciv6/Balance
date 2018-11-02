package com.peak.recycler.strategy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peak.recycler.base.BaseAdapter;
import com.peak.recycler.base.BaseHolder;

import java.util.List;


public class StrategyAdapter extends BaseAdapter<Object> {

    private List<? extends AbsStrategy> mStrategies;
    private long mLastClickTime;
    private static final long CLICK_INTERVAL = 200;


    public StrategyAdapter setStrategies(List<? extends AbsStrategy> strategies) {
        if (strategies == null && strategies.size() == 0) {
            throw new IllegalStateException("strategys can't be null or sise = 0");
        }
        mStrategies = strategies;
        return this;
    }

    @Override
    protected BaseHolder createBaseHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {

        return mStrategies.get(viewType).createViewHolder(inflater, parent);
    }


    @Override
    protected void bindViewData(BaseHolder baseHolder, int position, Object data, int viewType) {
        mStrategies.get(viewType).bindData(baseHolder, data, position);
    }

    @Override
    public int getItemViewType(int position) {
        Object object = getData(position);
        int idx = findItemViewType(object);
        if (idx >= 0) {
            return idx;
        }
        throw new IllegalStateException("No strategy to deal with: " + object);
    }

    public int findItemViewType(Object obj) {
        for (int i = 0, len = mStrategies.size(); i < len; i++) {
            if (mStrategies.get(i).canHandle(obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void onItemClick(View view, int position) {
        super.onItemClick(view, position);
        int viewType = getItemViewType(position);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > mLastClickTime + CLICK_INTERVAL) {
            AbsStrategy strategy = mStrategies.get(viewType);
            Object data = getData(position);
            strategy.onClicked(view, position, data);
            mLastClickTime = currentTimeMillis;
        }
    }

    @Override
    protected boolean onItemLongClick(View view, int position) {
        int viewType = getItemViewType(position);
        AbsStrategy strategy = mStrategies.get(viewType);
        Object data = getData(position);
        strategy.onLongClicked(view, position, data);
        return super.onItemLongClick(view, position);

    }

    public static abstract class AbsStrategy<T> {

        public abstract BaseHolder createViewHolder(LayoutInflater inflater, ViewGroup parent);

        public abstract void bindData(BaseHolder holder, T data, int position);

        public abstract void onClicked(View view, int position, T t);

        public abstract void onLongClicked(View view, int position, T t);

        public abstract boolean canHandle(Object obj);
    }

}
