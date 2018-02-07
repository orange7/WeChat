package com.thoughtworks.wechat.modules.moments.inject;

import com.thoughtworks.wechat.base.ActivityScope;
import com.thoughtworks.wechat.dagger.AppComponent;
import com.thoughtworks.wechat.modules.moments.MomentsActivity;
import com.thoughtworks.wechat.modules.moments.inject.MomentsModule;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lenovo on 2018/1/12.
 */
@ActivityScope
@Component(modules = MomentsModule.class, dependencies = AppComponent.class)
public interface MomentsComponent {
    void inject(MomentsActivity activity);
}
