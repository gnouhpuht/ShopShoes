package com.ghtk.ui.Views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ShopScrollView extends ScrollView {

    public boolean enableScrolling = true;

    public ShopScrollView(Context context) {
        super(context);
    }

    public ShopScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShopScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShopScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (enableScrolling) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (enableScrolling) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }
}
