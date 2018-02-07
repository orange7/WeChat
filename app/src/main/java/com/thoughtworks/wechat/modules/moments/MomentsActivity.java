package com.thoughtworks.wechat.modules.moments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thoughtworks.wechat.R;
import com.thoughtworks.wechat.adapter.TweetsAdapter;
import com.thoughtworks.wechat.base.BaseActivity;
import com.thoughtworks.wechat.bean.Tweet;
import com.thoughtworks.wechat.bean.User;
import com.thoughtworks.wechat.dagger.AppComponent;
import com.thoughtworks.wechat.modules.moments.inject.DaggerMomentsComponent;
import com.thoughtworks.wechat.modules.moments.inject.MomentsModule;
import java.util.List;

/**
 * Created by lenovo on 2018/1/12.
 */

public class MomentsActivity extends BaseActivity<MomentsContract.Presenter>
    implements MomentsContract.View, SwipeRefreshLayout.OnRefreshListener,
    BaseQuickAdapter.RequestLoadMoreListener {
  ImageView ivProfile;
  ImageView ivHead;
  TextView tvName;
  @BindView(R.id.ry_tweets) RecyclerView ryTweets;
  @BindView(R.id.layout_tweets) SwipeRefreshLayout layoutTweets;
  private TweetsAdapter mAdapter;
  private int mPage = 1;

  @Override public void getTweetsSuccessful(List<Tweet> result) {
    if(mPage == 1){
      mAdapter.getData().clear();
    }
    mAdapter.getData().addAll(result);
    mAdapter.notifyDataSetChanged();
    mAdapter.loadMoreComplete();
    layoutTweets.setRefreshing(false);
  }

  @Override public void getTweetsFailed(String error) {
    showMsg("getTweetsFailed = " + error);
  }

  @Override public void getUserSuccessful(User result) {
    Glide.with(mContext).load(result.getAvatar()).crossFade().into(ivHead);
    Glide.with(mContext)
        .load(result.getProfileimage())
        .placeholder(R.color.colorPrimary)
        .into(ivProfile);
    tvName.setText(result.getNick());
  }

  @Override public void getUserFailed(String error) {
    showMsg("getUserFailed = " + error);
  }

  @Override protected void setupActivityComponent(AppComponent appComponent) {
    DaggerMomentsComponent.builder()
        .appComponent(appComponent)
        .momentsModule(new MomentsModule(this))
        .build()
        .inject(this);
  }

  @Override protected View initView() {
    return LayoutInflater.from(mContext).inflate(R.layout.activity_moments, null, false);
  }

  @Override protected void initData() {
    ryTweets.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new TweetsAdapter();
    View headView = LayoutInflater.from(mContext).inflate(R.layout.layout_head, null, false);
    ivProfile = ButterKnife.findById(headView, R.id.iv_profile);
    ivHead = ButterKnife.findById(headView, R.id.iv_head);
    tvName = ButterKnife.findById(headView, R.id.tv_name);
    mAdapter.addHeaderView(headView);
    ryTweets.setAdapter(mAdapter);
    layoutTweets.setColorSchemeResources(R.color.colorPrimary);
    layoutTweets.setProgressViewOffset(false, 0,
        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
            getResources().getDisplayMetrics()));
    layoutTweets.setOnRefreshListener(this);
    mAdapter.setOnLoadMoreListener(this, ryTweets);
    mAdapter.setEnableLoadMore(true);
    layoutTweets.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override public void onGlobalLayout() {
            layoutTweets.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            layoutTweets.setRefreshing(true);
            mPresenter.getTweets("jsmith");
          }
        });
    mPresenter.getUserInfo("jsmith");
  }

  @Override public void onRefresh() {
    mPage = 1;
    mPresenter.getTweets("jsmith");
  }

  @Override public void onLoadMoreRequested() {
    mPage = mPage + 1;
    mPresenter.getTweets("jsmith");
  }
}
