package com.thoughtworks.wechat.modules.moments;

import com.thoughtworks.wechat.base.BaseModel;
import com.thoughtworks.wechat.bean.Tweet;
import com.thoughtworks.wechat.bean.User;
import com.thoughtworks.wechat.retrofit.ServiceManager;
import com.thoughtworks.wechat.retrofit.TweetsService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by lenovo on 2018/1/12.
 */

public class MomentsModel extends BaseModel implements MomentsContract.Model {

    @Inject
    ServiceManager mServiceManager;

    @Inject
    public MomentsModel() {

    }

    @Override
    public Observable<List<Tweet>> getTweets(String username) {
        return mServiceManager.mTweetsService.tweets(username);
    }

    @Override
    public Observable<User> getUserInfo(String username) {
        return  mServiceManager.mUserService.user(username);
    }

}
