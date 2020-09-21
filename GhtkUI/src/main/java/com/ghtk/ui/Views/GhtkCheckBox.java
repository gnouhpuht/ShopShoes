package com.ghtk.ui.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ghtk.ui.R;

/**
 * Created by Admin on 1/25/2018.
 */

public class GhtkCheckBox extends LinearLayout {

    public static int BOLD = 1;
    public static int BoldItalic = 2;
    public static int Medium = 3;
    public static int MediumItalic = 4;
    public static int Regular = 5;
    public static int RegularItalic = 6;
    public static int Thin = 7;
    public static int ThinItalic = 8;

    private TouchListener listener;

    public GhtkCheckBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createCheckBox(context, attrs);
    }

    private View rootView;
    private LinearLayout rootLayout;
    private ImageView icon;
    private TextView text;
    private boolean checked = false;

    private String textContent = "";
    private int textPadding = 0;
    private int textPaddingLeft = 0;
    private int textPaddingRight = 0;
    private int textPaddingTop = 0;
    private int textPaddingBottom = 0;
    private int textMargin = 0;
    private int textMarginLeft = 0;
    private int textMarginRight = 0;
    private int textMarginTop = 0;
    private int textMarginBottom = 0;

    private int textSize = 0;

    private int unCheckIconSrc = 0;
    private int checkIconSrc = 0;

    private void initView(Context context) {
        rootView = inflate(context, R.layout.custom_ghtk_checkbox, this);
        rootLayout = rootView.findViewById(R.id.custom_ghtk_checkbox_ll_root);
        rootLayout.setOnClickListener(view -> {
            doTouch();
            if (listener != null) {
                listener.onTouch();
            }
        });
        icon = rootView.findViewById(R.id.custom_ghtk_checkbox_icon);
        text = rootView.findViewById(R.id.custom_ghtk_checkbox_text);
    }

    public void setTouchListener(TouchListener listener) {
        this.listener = listener;
    }

    private void doTouch() {
        this.checked = !this.checked;
        setIconChecked();
    }

    private void createCheckBox(Context context, AttributeSet attributeSet) {
        initView(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.GhtkCheckBox, 0, 0);
        try {
            int fontText = a.getInt(R.styleable.GhtkCheckBox_font_text_checkbox, 10);
            this.setFontText(fontText);

            String text = a.getString(R.styleable.GhtkCheckBox_text);
            this.setText(text);

            this.textPadding = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textPadding, 0);
            this.textPaddingLeft = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textPaddingLeft, 0);
            this.textPaddingRight = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textPaddingRight, 0);
            this.textPaddingTop = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textPaddingTop, 0);
            this.textPaddingBottom = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textPaddingBottom, 0);
            setPaddingForText();

            this.textMargin = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textMargin, 0);
            this.textMarginLeft = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textMarginLeft, 0);
            this.textMarginRight = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textMarginRight, 0);
            this.textMarginTop = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textMarginTop, 0);
            this.textMarginBottom = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textMarginBottom, 0);
            setMarginForText();

            this.textSize = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_textSize, 0);
            setTextSize();

            int width = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_iconWidth, 0);
            int height = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_iconHeight, 0);
            setIconSize(width, height);

            int iconPadding = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_iconPadding, 0);
            setIconPadding(iconPadding);

            int iconMargin = a.getDimensionPixelSize(R.styleable.GhtkCheckBox_iconMargin, 0);
            setIconMargin(iconMargin);

            this.checked = a.getBoolean(R.styleable.GhtkCheckBox_checked, false);

            this.unCheckIconSrc = a.getInteger(R.styleable.GhtkCheckBox_iconUnCheck, R.drawable.ic_black_box_uncheck);
            this.checkIconSrc = a.getInteger(R.styleable.GhtkCheckBox_iconChecked, R.drawable.ic_black_box_checked);
            setIconChecked();

            int textColor = a.getColor(R.styleable.GhtkCheckBox_textColor, Color.BLACK);
            setTextColor(textColor);
        } finally {
            a.recycle();
        }
    }

    private void setTextColor(int color) {
        if (this.text == null) {
            return;
        }
        this.text.setTextColor(color);
    }

    private void setIconChecked() {
        if (this.checked) {
            setIconSrc(this.checkIconSrc);
        } else {
            setIconSrc(this.unCheckIconSrc);
        }
    }

    public boolean isChecked() {
        return this.checked;
    }

    private void setIconSrc(int resource) {
        if (this.icon == null) {
            return;
        }

        if (resource == 0) {
            return;
        }

        this.icon.setImageResource(resource);
    }

    private void setIconMargin(int margin) {
        if (this.icon == null) {
            return;
        }

        if (margin == 0) {
            return;
        }

        LayoutParams params = new LayoutParams(this.icon.getLayoutParams().width, this.icon.getLayoutParams().height);
        params.setMargins(margin, margin, margin, margin);
        this.icon.setLayoutParams(params);
    }

    private void setIconPadding(int padding) {
        if (this.icon == null) {
            return;
        }

        if (padding == 0) {
            return;
        }

        this.icon.setPadding(padding, padding, padding, padding);
    }

    private void setIconSize(int width, int height) {
        if (this.icon == null) {
            return;
        }

        if (width == 0 || height == 0) {
            return;
        }

        icon.getLayoutParams().width = width;
        icon.getLayoutParams().height = height;

        icon.requestLayout();
    }

    private void setTextSize() {
        if (this.text == null) {
            return;
        }

        if (this.textSize == 0) {
            return;
        }

        this.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.textSize);
    }

    private void setMarginForText() {
        if (this.text == null) {
            return;
        }

        if (this.textMargin != 0) {
            LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.setMargins(this.textMargin, this.textMargin, this.textMargin, this.textMargin);
            this.text.setLayoutParams(params);
            return;
        }

        if (this.textMarginLeft != 0 || this.textMarginTop != 0 || this.textMarginRight != 0 || this.textMarginBottom != 0) {
            LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.setMargins(this.textMarginLeft, this.textMarginTop, this.textMarginRight, this.textMarginBottom);
            this.text.setLayoutParams(params);
        }
    }

    private void setPaddingForText() {
        if (this.text == null) {
            return;
        }
        if (textPadding != 0) {
            this.text.setPadding(this.textPadding, this.textPadding, this.textPadding, this.textPadding);
            return;
        }

        if (this.textPaddingLeft != 0 || this.textPaddingTop != 0 || this.textPaddingRight != 0 || this.textPaddingBottom != 0) {
            this.text.setPadding(this.textPaddingLeft, this.textPaddingTop, this.textPaddingRight, this.textPaddingBottom);
        }
    }

    public void setChecked(boolean state) {
        this.checked = state;
        setIconChecked();
    }

    public void setText(String text) {
        if (text == null) {
            return;
        }

        this.textContent = text;
        this.text.setText(text);
    }

    public String getText() {
        return this.textContent;
    }

    public void setFamilyFont(int fontType) {
        if (fontType < 1 || fontType > 12) {
            return;
        }
        setFontText(fontType);
    }

    private void setFontText(int Type) {
        if (text == null) {
            return;
        }
        switch (Type) {
            case 1: {
                
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Bold.ttf"));
                break;
            }
            case 2: {
               
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_BoldItalic.ttf"));
                break;
            }
            case 3: {

                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf"));
                break;
            }
            case 4: {
                
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_MediumItalic.ttf"));
                break;
            }
            case 5: {
                
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
                break;
            }
            case 6: {
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Italic.ttf"));
                break;
            }
            case 7: {
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf"));
                break;
            }
            case 8: {
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_LightItalic.ttf"));
                break;
            }
            case 9: {

                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Condensed.ttf"));
                break;
            }
            case 10: {

                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_CondensedItalic.ttf"));
                break;
            }
            default:
                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
                break;
        }
//        switch (Type) {
//            case 1: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Bold.ttf"));
//                break;
//            }
//            case 2: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_BoldItalic.ttf"));
//                break;
//            }
//            case 3: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Condensed.ttf"));
//                break;
//            }
//            case 4: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_CondensedItalic.ttf"));
//                break;
//            }
//            case 5: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Italic.ttf"));
//                break;
//            }
//            case 6: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf"));
//                break;
//            }
//            case 7: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_LightItalic.ttf"));
//                break;
//            }
//            case 8: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf"));
//                break;
//            }
//            case 9: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_MediumItalic.ttf"));
//                break;
//            }
//            case 10: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
//                break;
//            }
//            case 11: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto/Roboto_Thin.ttf"));
//                break;
//            }
//            case 12: {
//                text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto/Roboto_ThinItalic.ttf"));
//                break;
//            }
//        }
    }
}
