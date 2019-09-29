package com.example.frame.http.utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Song Wenjun
 * Created by dell on 2018/12/20 18:58
 * Created self
 * package is com.example.dell.mvpupgrade.frame.base
 */
public abstract class BaseObserver implements Observer {
    private Disposable d;

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(Object value) {
        onBaseNext(value);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        onBaseError(e);
        dispose();
    }

    @Override
    public void onComplete() {

    }

    private void dispose() {
        if (!d.isDisposed()) {
            d.dispose();
        }
    }

    public abstract void onBaseNext(Object value);

    public abstract void onBaseError(Throwable e);
}
