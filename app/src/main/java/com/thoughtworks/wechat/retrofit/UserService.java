package com.thoughtworks.wechat.retrofit;

import com.thoughtworks.wechat.bean.Tweet;
import com.thoughtworks.wechat.bean.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lenovo on 2018/1/12.
 */

public interface UserService {
    @GET("user/{username}")
    Observable<User> user(@Path("username") String username);
}
