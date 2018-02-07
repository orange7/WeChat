package com.thoughtworks.wechat.modules.moments;

import com.thoughtworks.wechat.base.BasePresenter;
import com.thoughtworks.wechat.bean.Tweet;
import com.thoughtworks.wechat.bean.User;

import java.util.List;

import java.util.StringTokenizer;
import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/1/12.
 */
public class MomentsPresenter extends BasePresenter<MomentsContract.Model, MomentsContract.View> implements MomentsContract.Presenter {
    @Inject
    MomentsPresenter() {
    }

    @Override
    public void getTweets(final String username) {
        mModel.getTweets(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Tweet>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getTweetsFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Tweet> result) {
                        mView.getTweetsSuccessful(result);
                    }
                });

    }

    @Override
    public void getUserInfo(String username) {
        mModel.getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getUserFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(User result) {
                        mView.getUserSuccessful(result);
                    }
                });
    }

    public void test(){
      Observable.just(123).flatMap(new Func1<Integer, Observable<String>>() {
        @Override public Observable<String> call(Integer integer) {
          return Observable.just("sss: " + integer);
        }
      }).subscribe(new Subscriber<String>() {
        @Override public void onCompleted() {

        }

        @Override public void onError(Throwable e) {

        }

        @Override public void onNext(String s) {

        }
      });

    }

}
