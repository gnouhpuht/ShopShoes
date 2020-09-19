package com.ghtk.ui.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghtk.ui.R;

/**
 * Created by DatUet on 4/23/2018.
 */

public class GhtkChooseInforLayout extends RelativeLayout{

    RelativeLayout rootView;
    ImageView upDowIv;
    TextView titleTv;

    private GhtkChooseInforListener ghtkChooseInforListener;

    public GhtkChooseInforLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public GhtkChooseInforLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public GhtkChooseInforLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.choose_infor_layout_content, this, true);
        }
        titleTv = findViewById(R.id.titleTv);
        upDowIv = findViewById(R.id.upDowIv);
        rootView = findViewById(R.id.rootView);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ChooseInforLayout, defStyle, 0);

        if (a.hasValue(R.styleable.ChooseInforLayout_choose_infor_right_img)) {
            upDowIv.setImageDrawable(a.getDrawable(R.styleable.ChooseInforLayout_choose_infor_right_img));
        }

        if (a.hasValue(R.styleable.ChooseInforLayout_choose_infor_title)) {
            titleTv.setText(a.getString(R.styleable.ChooseInforLayout_choose_infor_title));
        }

        rootView.setOnClickListener(view -> ghtkChooseInforListener.onClick());
    }

    public GhtkChooseInforListener getGhtkChooseInforListener() {
        return ghtkChooseInforListener;
    }

    public void setGhtkChooseInforListener(GhtkChooseInforListener ghtkChooseInforListener) {
        this.ghtkChooseInforListener = ghtkChooseInforListener;
    }

    public String getTitle(){
        return titleTv.getText().toString().trim();
    }

    public void setTitle(String title){
        if(null != title)
            titleTv.setText(title);
    }

    public interface GhtkChooseInforListener {
        void onClick();
    }

}
