package com.example.frame.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.frame.interfaces.ICommonModule;
import com.example.frame.interfaces.ICommonView;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 16:25
 * Created prepare
 * package is com.example.frame.base
 * <p>
 * This class is used to do:
 */
public abstract class BaseNetFragment<P extends BasePresenter, M extends ICommonModule> extends BaseFragment implements ICommonView {

    public P presenter;
    public M module;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(createLayout(), null);

        initView(inflate);

        presenter = createPresenter();
        module = createModule();

        presenter.attach(this, module);

        initData();

        return inflate;
    }

    protected abstract void initData();

    protected abstract M createModule();

    protected abstract P createPresenter();

    protected void initView(View inflate) {
    }

    protected abstract int createLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettach();
        }
    }
}
