package com.example.frame.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.frame.interfaces.ICommonModule;
import com.example.frame.interfaces.ICommonPresenter;
import com.example.frame.interfaces.ICommonView;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 16:18
 * Created prepare
 * package is com.example.frame.base
 * <p>
 * This class is used to do:超级网络Activity
 */
public abstract class BaseNetActivity<P extends BasePresenter, M extends ICommonModule> extends BaseActivity implements ICommonView {

    public M module;
    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayout());

        presenter = createPresenter();
        module = createModule();

        presenter.attach(this, module);

        initView();
        initData();
    }

    protected void initData() {
    }


    protected void initView() {
    }

    protected abstract M createModule();

    protected abstract P createPresenter();

    public abstract int createLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettach();
        }
    }
}
