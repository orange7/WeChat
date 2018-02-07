package com.thoughtworks.wechat.retrofit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lenovo on 2018/1/12.
 */
@Singleton
public class ServiceManager {

    @Inject
    public UserService mUserService;

    @Inject
    public TweetsService mTweetsService;

    @Inject
    public ServiceManager(){}

}