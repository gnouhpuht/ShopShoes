package com.ghtk.ui.Views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ghtk.ui.R;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GHTKFlowViewTagsProducts<T>  extends FlowLayout {

    List<View> mChildViews;
    ArrayList<String> mMapTags;

    private View tagViewAction;
//    private boolean hasActionTag = false;
    public GHTKFlowViewTagsProducts(Context context) {
        super(context);
        initView();
    }

    public ArrayList<String> getMapTags() {
        return mMapTags;
    }

    public GHTKFlowViewTagsProducts<T> setDataTags(ArrayList<String> mapTags) {
        mMapTags = mapTags;
        return this;
    }

    public GHTKFlowViewTagsProducts(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView() {
        mChildViews = new ArrayList<>();
        mMapTags = new ArrayList<String>();
    }

    public void setTags(final ArrayList<String> tags) {
//        mMapTags.clear();
//        mMapTags.putAll(tags);
        mMapTags = tags;
        mChildViews.clear();
        removeAllViews();
        for (final String tag : tags) {
            final View tagView = LayoutInflater.from(getContext()).inflate(R.layout.item_tags_product, null);
            final GhtkTextView tagTv = tagView.findViewById(R.id.content_tv);
            tagTv.setText(tag);
            addView(tagView);


            int pL = tagTv.getPaddingLeft();
            int pT = tagTv.getPaddingTop();
            int pR = tagTv.getPaddingRight();
            int pB = tagTv.getPaddingBottom();

            tagTv.setPadding(pL, pT, pR, pB);

            final ImageView deleteImg = tagView.findViewById(R.id.delete_iv);

            deleteImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeView(tagView);
                    tags.remove(tag);
                }
            });
        }


        invalidate();
    }
    public GHTKFlowViewTagsProducts<T> setHasActionTag(boolean hasActionTag) {
        return this;
    }

    private GHTKFlowViewTagsProducts.OnAddTagClickListener mOnAddTagClickListener;
    private boolean showAddTagView = false;

    public GHTKFlowViewTagsProducts<T> setOnActionTagClick(GHTKFlowViewTagsProducts.OnAddTagClickListener onClickListener) {
        this.mOnAddTagClickListener = onClickListener;
        return this;
    }

    public interface OnAddTagClickListener {
        void onShowAddTag(boolean show);
    }
}
