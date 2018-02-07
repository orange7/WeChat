package com.thoughtworks.wechat.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.thoughtworks.wechat.WeChatApplication;
import com.thoughtworks.wechat.dagger.AppComponent;
import javax.inject.Inject;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2018/1/12.
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {
    protected WeChatApplication mApplication;
    private Unbinder mUnbinder;
    @Inject
    protected P mPresenter;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (WeChatApplication) getApplication();
        mContext = this;
        setContentView(initView());
        mUnbinder = ButterKnife.bind(this);
        setupActivityComponent(mApplication.getAppComponent());
        initData();
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) mUnbinder.unbind();
        this.mPresenter = null;
        this.mUnbinder = null;
        this.mApplication = null;
    }
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    protected abstract View initView();

    protected abstract void initData();


}