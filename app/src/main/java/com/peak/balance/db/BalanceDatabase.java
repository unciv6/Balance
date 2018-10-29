package com.peak.balance.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.peak.balance.base.BaseApp;
import com.peak.balance.db.bean.Expend;
import com.peak.balance.db.dao.ExpendDao;


@Database(entities = Expend.class, version = 1)
public abstract class BalanceDatabase extends RoomDatabase {

    private static BalanceDatabase sInstance;

    public abstract ExpendDao getExpendDao();

    public static BalanceDatabase getInstance() {
        synchronized (BalanceDatabase.class) {
            if (sInstance == null) {
                sInstance = Room.databaseBuilder(BaseApp.getContext()
                        , BalanceDatabase.class, "balance.db"
                ).build();
            }
            return sInstance;
        }
    }


}
