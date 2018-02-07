package com.thoughtworks.wechat.modules.moments;

import com.thoughtworks.wechat.base.IModel;
import com.thoughtworks.wechat.base.IPresenter;
import com.thoughtworks.wechat.base.IView;
import com.thoughtworks.wechat.bean.Tweet;
import com.thoughtworks.wechat.bean.User;
import java.util.List;
import rx.Observable;

/**
 * Created by lenovo on 2018/1/12.
 */

public interface MomentsContract {
    interface View extends IView {
        /**
         * 获取推特列表成功
         * @param result
         */
        void getTweetsSuccessful(List<Tweet> result);

        /**
         * 获取推特列表失败
         * @param error
         */
        void getTweetsFailed(String error);

        /**
         * 获取用户信息成功
         * @param result
         */
        void getUserSuccessful(User result);

        /**
         * 获取用户信息失败
         * @param error
         */
        void getUserFailed(String error);
    }

    interface Presenter extends IPresenter {

        /**
         * 获取推特列表
         * @param username
         */
        void getTweets(String username);

        /**
         * 获取用户信息
         * @param username
         */
        void getUserInfo(String username);

    }

    interface Model extends IModel {

        /**
         * 获取推特列表
         * @param username
         */
        Observable<List<Tweet>> getTweets(String username);

        /**
         * 获取用户信息
         * @param username
         */
        Observable<User> getUserInfo(String username);

    }
}
