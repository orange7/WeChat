package com.thoughtworks.wechat;

import android.app.Application;
import com.thoughtworks.wechat.dagger.AppComponent;
import com.thoughtworks.wechat.dagger.AppModule;
import com.thoughtworks.wechat.dagger.DaggerAppComponent;

/**
 * Created by lenovo on 2018/1/12.
 */

public class WeChatApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
