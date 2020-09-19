package com.ghtk.ui.Views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.ghtk.ui.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GHTKFlowView<T> extends FlowLayout {
    List<View> mChildViews;
    Map<T, Boolean> mMapTags;

    private boolean hasActionTag = false;
    private View tagViewAction;

    public GHTKFlowView(Context context) {
        super(context);
        initView();
    }

    public GHTKFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mChildViews = new ArrayList<>();
        mMapTags = new HashMap<>();
    }

    public void setTags(final Map<T, Boolean> tags) {
//        mMapTags.clear();
//        mMapTags.putAll(tags);
        mMapTags = tags;
        mChildViews.clear();
        removeAllViews();
        for (final Map.Entry<T, Boolean> entry : tags.entrySet()) {
            final View tagView = LayoutInflater.from(getContext()).inflate(R.layout.item_tags, null);
            tagView.setTag(entry.getKey());
            final GhtkTextView tagTv = tagView.findViewById(R.id.content_tv);
            tagTv.setText(entry.getKey().toString());
            addView(tagView);
            tagView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pL = tagTv.getPaddingLeft();
                    int pT = tagTv.getPaddingTop();
                    int pR = tagTv.getPaddingRight();
                    int pB = tagTv.getPaddingBottom();
                    if (mMapTags.get(view.getTag()) != null && mMapTags.get(view.getTag())) {
                        mMapTags.put((T) view.getTag(), false);
                        tagTv.setBackgroundResource(R.drawable.bg_white_round_radius_5dp);
                        tagTv.setTextColor(Color.BLACK);
                    } else {
                        mMapTags.put((T) view.getTag(), true);
                        tagTv.setBackgroundResource(R.drawable.bg_green_round_radius_5dp);
                        tagTv.setTextColor(Color.WHITE);
                    }
                    tagTv.setPadding(pL, pT, pR, pB);
                }
            });

            int pL = tagTv.getPaddingLeft();
            int pT = tagTv.getPaddingTop();
            int pR = tagTv.getPaddingRight();
            int pB = tagTv.getPaddingBottom();
            if (mMapTags.get(tagView.getTag()) != null && mMapTags.get(tagView.getTag())) {
                tagTv.setBackgroundResource(R.drawable.bg_green_round_radius_5dp);
                tagTv.setTextColor(Color.WHITE);
            } else {
                tagTv.setBackgroundResource(R.drawable.bg_white_round_radius_5dp);
                tagTv.setTextColor(Color.BLACK);
            }
            tagTv.setPadding(pL, pT, pR, pB);
        }

        if (hasActionTag) {
            tagViewAction = LayoutInflater.from(getContext()).inflate(R.layout.item_tags, null);
            final GhtkTextView tagTv = tagViewAction.findViewById(R.id.content_tv);

            int pL = tagTv.getPaddingLeft();
            int pT = tagTv.getPaddingTop();
            int pR = tagTv.getPaddingRight();
            int pB = tagTv.getPaddingBottom();
            tagTv.setBackgroundResource(R.drawable.bg_white_round_radius_nostroke_5dp);
            tagTv.setPadding(pL, pT, pR, pB);
            tagTv.setText("Nhập tag");
            if (mOnAddTagClickListener != null) {
                tagViewAction.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showAddTagView = !showAddTagView;

                        int pL = tagTv.getPaddingLeft();
                        int pT = tagTv.getPaddingTop();
                        int pR = tagTv.getPaddingRight();
                        int pB = tagTv.getPaddingBottom();
                        tagTv.setBackgroundResource(showAddTagView ? R.drawable.bg_green_round_radius_nostroke_5dp : R.drawable.bg_white_round_radius_nostroke_5dp);
                        tagTv.setPadding(pL, pT, pR, pB);
                        tagTv.setTextColor(showAddTagView ? Color.WHITE : Color.BLACK);

                        mOnAddTagClickListener.onShowAddTag(showAddTagView);
                    }
                });
            }
            addView(tagViewAction);
        }
        invalidate();
    }

    public GHTKFlowView<T> setHasActionTag(boolean hasActionTag) {
        this.hasActionTag = hasActionTag;
        return this;
    }

    private OnAddTagClickListener mOnAddTagClickListener;
    private boolean showAddTagView = false;

    public GHTKFlowView<T> setOnActionTagClick(OnAddTagClickListener onClickListener) {
        this.mOnAddTagClickListener = onClickListener;
        if (tagViewAction != null) {
            tagViewAction.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddTagView = !showAddTagView;
                    GhtkTextView tagTv = tagViewAction.findViewById(R.id.content_tv);
                    int pL = tagTv.getPaddingLeft();
                    int pT = tagTv.getPaddingTop();
                    int pR = tagTv.getPaddingRight();
                    int pB = tagTv.getPaddingBottom();
                    tagTv.setBackgroundResource(showAddTagView ? R.drawable.bg_green_round_radius_nostroke_5dp : R.drawable.bg_white_round_radius_nostroke_5dp);
                    tagTv.setPadding(pL, pT, pR, pB);
                    tagTv.setTextColor(showAddTagView ? Color.WHITE : Color.BLACK);
                    if (mOnAddTagClickListener != null) {
                        mOnAddTagClickListener.onShowAddTag(showAddTagView);
                    }
                }
            });
        }
        return this;
    }

    public interface OnAddTagClickListener {
        void onShowAddTag(boolean show);
    }
}
