package com.thoughtworks.wechat.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.thoughtworks.wechat.R;
import com.thoughtworks.wechat.bean.Tweet;
import java.util.ArrayList;
import java.util.List;

public class CommentListView extends LinearLayout {
    private List<Tweet.CommentsBean> mDatas;
    private LayoutInflater layoutInflater ;

    public void setDatas(List<Tweet.CommentsBean> datas){
        if(datas == null ){
            datas = new ArrayList<>();
        }
        mDatas = datas;
        notifyDataSetChanged();
    }

    public List<Tweet.CommentsBean> getDatas(){
        return mDatas;
    }

    public CommentListView(Context context) {
        super(context);
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void notifyDataSetChanged(){

        removeAllViews();
        if(mDatas == null || mDatas.size() == 0){
            return;
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(int i=0; i<mDatas.size(); i++){
            final int index = i;
            View view = getView(index);
            if(view == null){
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }

            addView(view, index, layoutParams);
        }

    }

    private View getView(final int position){
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(getContext());
        }
        View convertView = layoutInflater.inflate(R.layout.item_comment, null, false);
        TextView commentTv = convertView.findViewById(R.id.commentTv);
        final Tweet.CommentsBean bean = mDatas.get(position);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(setClickableSpan(bean.getSender().getNick()));
        builder.append(": ");
        builder.append(bean.getContent());
        commentTv.setText(builder);
        return convertView;
    }

    @NonNull
    private SpannableString setClickableSpan(final String textStr) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new ClickableSpan(){
                                    @Override
                                    public void onClick(View widget) {
                                    }
                                }, 0, subjectSpanText.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

}
