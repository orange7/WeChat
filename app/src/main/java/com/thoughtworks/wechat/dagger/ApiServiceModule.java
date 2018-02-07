package com.thoughtworks.wechat.dagger;

import com.thoughtworks.wechat.retrofit.TweetsService;
import com.thoughtworks.wechat.retrofit.UserService;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/**
 * Created by lenovo on 2018/1/12.
 */

@Module
public class ApiServiceModule {

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return HttpUrl.parse("http://thoughtworks-ios.herokuapp.com/");
    }

    @Singleton
    @Provides
    TweetsService provideTweetsService(Retrofit retrofit) {
        return retrofit.create(TweetsService.class);
    }

    @Singleton
    @Provides
    UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }


}