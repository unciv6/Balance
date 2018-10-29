package com.peak.balance.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    private static BaseApp sContext;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = this;
    }

    public static BaseApp getContext() {
        return sContext;
    }
}
