package com.example.frame.base;

import com.example.frame.interfaces.ICommonModule;
import com.example.frame.interfaces.ICommonView;

import java.lang.ref.WeakReference;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 14:17
 * Created prepare
 * package is com.example.frame.base
 * <p>
 * This class is used to do:
 */
public class BasePresenter<V extends ICommonView, M extends ICommonModule> {
    private WeakReference<V> view;
    private WeakReference<M> module;

    /**
     * 包裹视图
     *
     * @param view
     * @param module
     */
    public void attach(V view, M module) {
        this.view = new WeakReference<V>(view);
        this.module = new WeakReference<M>(module);
    }

    /**
     * 销毁视图，解除绑定关系，避免内存泄漏
     */
    public void dettach() {
        if (module != null) {
            module.clear();
            module = null;
        }
        if (view != null) {
            view.clear();
            view = null;
        }
    }

    public M getModule() {
        return module == null ? null : module.get();
    }

    public V getView() {
        return view == null ? null : view.get();
    }
}
