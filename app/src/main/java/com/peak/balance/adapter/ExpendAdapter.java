package com.peak.balance.adapter;


import com.peak.recycler.strategy.StrategyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ExpendAdapter extends StrategyAdapter {


    private List<AbsStrategy> mStrategies;

    private ExpendStrategy mExpendStrategy;
    private TempStrategy mTempStrategy;

    public ExpendAdapter() {
        mStrategies = new ArrayList<>();
        mExpendStrategy = new ExpendStrategy();
        mTempStrategy = new TempStrategy();
        mStrategies.add(mExpendStrategy);
        mStrategies.add(mTempStrategy);
        setStrategies(mStrategies);
    }
}
