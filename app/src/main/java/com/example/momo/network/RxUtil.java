package com.example.momo.network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtil {

    private final static Observable.Transformer schedulersTransformer = new  Observable.Transformer() {
        @Override public Object call(Object observable) {
            return ((Observable)  observable).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static  <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
//Observable
//        .just( "123" )
//        .compose( RxUtil.<String>applySchedulers() )
//        .subscribe(new Action1() {
//@Override
//public void call(Object o) {
//        }
//        }) ;