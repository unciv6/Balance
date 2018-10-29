package com.peak.balance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.peak.balance.db.BalanceDatabase;
import com.peak.balance.db.bean.Expend;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mThread.start();

    }

    Thread mThread = new Thread() {
        @Override
        public void run() {
            super.run();
            BalanceDatabase.getInstance().getExpendDao().insertRecord(new Expend());

            Log.i("TEST", BalanceDatabase.getInstance().getExpendDao().getAll().toString());
        }
    };
}
