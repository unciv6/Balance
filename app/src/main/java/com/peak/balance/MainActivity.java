package com.peak.balance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.peak.balance.db.BalanceDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BalanceDatabase.getInstance().getExpendDao().insertRecord();
    }
}
