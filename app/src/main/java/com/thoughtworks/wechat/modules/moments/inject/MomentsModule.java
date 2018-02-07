package com.thoughtworks.wechat.modules.moments.inject;

import com.thoughtworks.wechat.modules.moments.MomentsContract;
import com.thoughtworks.wechat.modules.moments.MomentsModel;
import com.thoughtworks.wechat.modules.moments.MomentsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lenovo on 2018/1/12.
 */
@Module
public class MomentsModule {
    private MomentsContract.View view;

    public MomentsModule(MomentsContract.View view) {
        this.view = view;
    }

    @Provides
    MomentsContract.Model provideModel(MomentsModel model) {
        return model;
    }

    @Provides
    MomentsContract.View provideView() {
        return this.view;
    }


    @Provides
    MomentsContract.Presenter providePresenter(MomentsPresenter presenter) {
        return presenter;
    }
}
