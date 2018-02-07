package com.thoughtworks.wechat.retrofit;

import com.thoughtworks.wechat.bean.Tweet;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lenovo on 2018/1/12.
 */

public interface TweetsService {
    @GET("user/{username}/tweets")
    Observable<List<Tweet>> tweets(@Path("username") String username);

}
