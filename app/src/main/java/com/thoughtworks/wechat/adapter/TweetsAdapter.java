package com.thoughtworks.wechat.adapter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.thoughtworks.wechat.R;
import com.thoughtworks.wechat.bean.Tweet;
import com.thoughtworks.wechat.widgets.CommentListView;
import com.thoughtworks.wechat.widgets.MultiImageView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2018/1/12.
 */

public class TweetsAdapter extends BaseQuickAdapter<Tweet, BaseViewHolder> {
    public TweetsAdapter() {
        super(R.layout.item_tweet, new ArrayList<Tweet>());
    }
    @Override
    protected void convert(BaseViewHolder helper, Tweet item) {
        if(item.getSender() != null){
            if(item.getSender().getNick() != null){
                helper.setText(R.id.tv_name, item.getSender().getNick());
            }
            if(item.getSender().getAvatar() != null){
                Glide.with(mContext).load(item.getSender().getAvatar()).placeholder(R.drawable.ic_def_head).crossFade().into((ImageView) helper.getView(R.id.iv_head));
            }
        }
        TextView tvContent = helper.getView(R.id.tv_content);
        if(TextUtils.isEmpty(item.getContent())){
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(item.getContent());
        }

        MultiImageView multiImageView = helper.getView(R.id.multiImagView);
        if(item.getImages() != null && item.getImages().size() > 0){
            multiImageView.setVisibility(View.VISIBLE);
            multiImageView.setList(item.getImages());
        } else {
            multiImageView.setVisibility(View.GONE);
        }
        CommentListView commentListView = helper.getView(R.id.commentListViw);
        if(item.getComments() != null && item.getComments().size() > 0){
            commentListView.setVisibility(View.VISIBLE);
            commentListView.setDatas(item.getComments());
        } else {
            commentListView.setVisibility(View.GONE);
        }
    }
}
