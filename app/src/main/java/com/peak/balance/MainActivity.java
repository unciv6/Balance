package com.peak.balance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.peak.balance.adapter.ExpendAdapter;
import com.peak.balance.db.BalanceDatabase;
import com.peak.balance.db.bean.Expend;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.number)
    EditText mNumber;
    @BindView(R.id.category)
    Spinner mCategory;
    @BindView(R.id.extra)
    EditText mExtra;

    ExpendAdapter mAdapter;

    String cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initWidget();
        initData();
    }

    private void initWidget() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cate = (getResources().getStringArray(R.array.category))[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initData() {
        //todo delete
        mNumber.setText(String.valueOf(new Random().nextInt(1000)));
        mAdapter = new ExpendAdapter();
        mAdapter.add(buildFakeData());
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.button)
    public void addData() {
        final String amount = mNumber.getText().toString().trim();
        final String category = cate;
        final String extras = mExtra.getText().toString().trim();

        if (amount.length() == 0) {
            return;
        }
        mRecyclerView.getLayoutManager().scrollToPosition(0);
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {

                Expend expend = new Expend(Float.valueOf(amount), category, extras);
                mAdapter.addByIndex(0, expend);
                BalanceDatabase.getInstance().getExpendDao().insertRecord(expend);
                emitter.onNext(true);
                emitter.onComplete();
                Log.i(TAG, "subscribe: " + BalanceDatabase.getInstance().getExpendDao().getAll().toString());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        Log.i(TAG, "onNext: 插入成功");
                        Toast.makeText(getApplicationContext(), "插入成功", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private List buildFakeData() {

        Observable.create(new ObservableOnSubscribe<List<Expend>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Expend>> emitter) throws Exception {
                emitter.onNext(BalanceDatabase.getInstance().getExpendDao().getAll());
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Expend>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Expend> expends) {
                        mAdapter.add(expends);
                        Log.i(TAG, "onNext: 查找数据成功");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

        return null;
    }
}
