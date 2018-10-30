package com.peak.balance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.peak.balance.db.BalanceDatabase;
import com.peak.balance.db.bean.Expend;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.number)
    EditText mNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNumber.setText(new Random().nextFloat() + "");
    }


    @OnClick(R.id.button)
    public void onClick() {
        Thread mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                Expend expend = new Expend(Float.valueOf(mNumber.getText().toString()), "吃喝", null);
                BalanceDatabase.getInstance().getExpendDao().insertRecord(expend);

                Log.i("TEST", BalanceDatabase.getInstance().getExpendDao().getAll().toString());
            }
        };
        mThread.start();
        mNumber.setText(new Random().nextFloat() + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
