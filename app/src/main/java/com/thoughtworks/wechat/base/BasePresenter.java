package com.thoughtworks.wechat.base;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by lenovo on 2018/1/12.
 */

public class BasePresenter<M extends IModel, V extends IView> {
    @Inject
    protected M mModel;
    @Inject
    protected V mView;

    public BasePresenter() {
    }

    protected Context getContext(){
        return (Context) mView;
    }
}