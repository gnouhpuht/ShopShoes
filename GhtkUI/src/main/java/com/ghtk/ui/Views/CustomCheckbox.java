package com.ghtk.ui.Views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ghtk.ui.R;

/**
 * Created by Admin on 2/1/2018.
 */

public class CustomCheckbox extends LinearLayout {

    public static int BOLD = 1;
    public static int BoldItalic = 2;
    public static int Condensed = 3;
    public static int CondensedItalic = 4;
    public static int Italic = 5;
    public static int Light = 6;
    public static int LightItalic = 7;
    public static int Medium = 8;
    public static int MediumItalic = 9;
    public static int Regular = 10;
    public static int Thin = 11;
    public static int ThinItalic = 12;

    private View rootView;
    private GhtkTextView text;
    private ImageView icon;
    private LinearLayout llRoot;
    private int emptyCheckBox_icon = R.drawable.ic_check_box_outline_blank_black_24dp;
    private int checkBox_icon = R.drawable.ic_check_box_white_24dp;
    private boolean checked = false;
    private CustomCheckboxListener listener;

    public CustomCheckbox(Context context) {
        super(context);
        init(context);
    }

    public CustomCheckbox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomCheckbox, 0, 0);

        try {
            if (text != null) {
                int style = a.getInteger(R.styleable.CustomCheckbox_font_text_custom_checkbox, 10);
                setFontForText(style);
                text.setText(a.getString(R.styleable.CustomCheckbox_setText));
                int textSize = a.getDimensionPixelSize(R.styleable.CustomCheckbox_setFontSize, 0);
                if (textSize != 0) {
                    setTextSize(textSize);
                }
                int textPaddingLeft = a.getDimensionPixelSize(R.styleable.CustomCheckbox_text_padding_left, 0);
                if (textPaddingLeft != 0) {
                    int top = text.getPaddingTop();
                    int right = text.getPaddingRight();
                    int bottom = text.getPaddingBottom();
                    text.setPadding(textPaddingLeft, top, right, bottom);
                }
            }

            if (icon != null) {
                setChecked(a.getBoolean(R.styleable.CustomCheckbox_setChecked, false));
            }

            int iconWidth = (int) (22 * Resources.getSystem().getDisplayMetrics().density);
            iconWidth = a.getDimensionPixelSize(R.styleable.CustomCheckbox_icon_Checkbox_Width, iconWidth);
            icon.getLayoutParams().width = iconWidth;

            int iconHeight = iconWidth;
            iconHeight = a.getDimensionPixelSize(R.styleable.CustomCheckbox_icon_Checkbox_Height, iconHeight);
            icon.getLayoutParams().height = iconHeight;

            int unCheckIcon = a.getResourceId(R.styleable.CustomCheckbox_uncheckIcon, 0);
            if (unCheckIcon != 0) {
                emptyCheckBox_icon = unCheckIcon;
            }

            int checkedIcon = a.getResourceId(R.styleable.CustomCheckbox_checkIcon, 0);
            if (checkedIcon != 0) {
                checkBox_icon = checkedIcon;
            }

            int padding = a.getDimensionPixelSize(R.styleable.CustomCheckbox_icon_Checkbox_Padding, 0);
            if (padding != 0) {
                icon.setPadding(padding, padding, padding, padding);
            }

            setIconCheckBox(emptyCheckBox_icon, checkBox_icon);

            int textColor = a.getColor(R.styleable.CustomCheckbox_text_color, 0);
            if (textColor != 0) {
                text.setTextColor(textColor);
            }
        } finally {
            a.recycle();
        }
    }

    public void setFontForText(int Type) {
        this.text.setFontForText(Type);
//        switch (Type) {
//            case 1: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Bold.ttf"));
//                break;
//            }
//            case 2: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_BoldItalic.ttf"));
//                break;
//            }
//            case 3: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Condensed.ttf"));
//                break;
//            }
//            case 4: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_CondensedItalic.ttf"));
//                break;
//            }
//            case 5: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Italic.ttf"));
//                break;
//            }
//            case 6: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf"));
//                break;
//            }
//            case 7: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_LightItalic.ttf"));
//                break;
//            }
//            case 8: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf"));
//                break;
//            }
//            case 9: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_MediumItalic.ttf"));
//                break;
//            }
//            case 10: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
//                break;
//            }
//            case 11: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Thin.ttf"));
//                break;
//            }
//            case 12: {
//                this.text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_ThinItalic.ttf"));
//                break;
//            }
//        }
    }

    public CustomCheckbox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.custom_checkbox, this);
        llRoot = rootView.findViewById(R.id.custom_checkbox_ll_root);

        llRoot.setOnClickListener(view -> clickChangeState());
        //llRoot.setClickable(false);

        text = rootView.findViewById(R.id.custom_checkbox_text);

        text.setOnClickListener(view -> clickChangeState());
        //text.setClickable(false);

        icon = rootView.findViewById(R.id.custom_checkbox_icon);

        icon.setOnClickListener(view -> clickChangeState());
        ///icon.setClickable(false);

        this.listener = null;
    }

    public void setEnableClickedInside(boolean state) {
        llRoot.setClickable(state);
        text.setClickable(state);
        icon.setClickable(state);
    }

    public void setCustomCheckboxListener(CustomCheckboxListener customCheckboxListener) {
        this.listener = customCheckboxListener;
    }

    public void setChecked(boolean state) {
        this.checked = state;
        changeIcon(checked);
    }

    public void toggleStateCheckbox() {
        if (this.checked) {
            setChecked(false);
        } else {
            setChecked(true);
        }
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setText(String txt) {
        if (this.text != null) {
            this.text.setText(txt);
        }
    }

    public void setTextColor(String color) {
        if (this.text != null) {
            this.text.setTextColor(Color.parseColor(color));
        }
    }

    public void setTextSize(float fontSize) {
        if (this.text != null) {
            this.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
        }
    }

    public void setBackgroundColor(String color) {
        if (this.llRoot != null) {
            this.llRoot.setBackgroundColor(Color.parseColor(color));
        }
    }

    public void setBackgroundDrawable(int drawable) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            this.llRoot.setBackgroundDrawable(getResources().getDrawable(drawable));
        } else {
            this.llRoot.setBackground(getResources().getDrawable(drawable));
        }
    }

    public void setIconCheckBox(int drawableIconEmpty, int drawableIconChecked) {
        this.emptyCheckBox_icon = drawableIconEmpty;
        this.checkBox_icon = drawableIconChecked;
        changeIcon(checked);
    }

    private void changeIcon(boolean state) {
        Log.e("@@@@@@", state? "Checked" : "Not Checked");
        if (icon != null) {
            if (state) {
                icon.setImageDrawable(getResources().getDrawable(checkBox_icon));
            } else {
                icon.setImageDrawable(getResources().getDrawable(emptyCheckBox_icon));
            }
        }
//        invalidate();
    }

    private void clickChangeState() {
        Log.e("@@@@@", "Click Change State");
        checked = !checked;
        changeIcon(checked);

        if (listener != null) {
            listener.onEvent();
        }
    }

    public void setIconPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        if (icon != null) {
            icon.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    public interface CustomCheckboxListener {
        void onEvent();
    }
}
