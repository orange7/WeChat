package com.thoughtworks.wechat.dagger;

import android.app.Application;

import com.google.gson.Gson;
import com.thoughtworks.wechat.WeChatApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/1/12.
 */

@Module
public class AppModule {

    public AppModule(WeChatApplication application) {
        this.mApplication = application;
    }

    private WeChatApplication mApplication;

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Gson provideGson(){return new Gson();}

}