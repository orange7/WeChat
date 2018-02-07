package com.thoughtworks.wechat.dagger;

import android.app.Application;

import com.google.gson.Gson;
import com.thoughtworks.wechat.retrofit.ServiceManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lenovo on 2018/1/12.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, ApiServiceModule.class})
public interface AppComponent {
    Application application();
    ServiceManager serviceManager();
    Gson gson();
}
