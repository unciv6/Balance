package com.peak.balance.util;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gaofeng on 12/29/18.
 */
public class RxUtils {

    public static <T> FlowableTransformer<T, T> flowableSchedule() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> flowable) {
                return flowable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(new Function<Throwable, Publisher<? extends T>>() {
                            @Override
                            public Publisher<? extends T> apply(Throwable throwable) throws Exception {
                                throw new CompositeException(throwable);
                            }
                        });
            }
        };
    }
}
