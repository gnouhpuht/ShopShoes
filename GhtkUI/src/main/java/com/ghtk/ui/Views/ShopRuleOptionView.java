package com.ghtk.ui.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ghtk.ui.R;

public class ShopRuleOptionView extends LinearLayout {

    public static final int BOLD = 13;
    public static final int BOLD_ITALIC = 14;
    public static final int CONDENSED_BLACK = 15;
    public static final int CONDENSED_BOLD = 16;
    public static final int ITALIC = 17;
    public static final int LIGHT = 18;
    public static final int LIGHT_ITALIC = 19;
    public static final int MEDIUM = 20;
    public static final int REGULAR = 21;
    public static final int ULTRALIGHT = 22;
    public static final int ULTRALIGHT_ITALIC = 23;

    private IFTouchViewItem<String> listener;

    public void setViewTouchedListener(IFTouchViewItem<String> callback) {
        this.listener = callback;
    }

    public ShopRuleOptionView(Context context) {
        super(context);
        init(context);
    }

    boolean isEditTextEnable = false;

    public ShopRuleOptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShopRuleOptionView, 0, 0);

        try {
            if (txtContent != null) {
//                int style = a.getInteger(R.styleable.ShopRuleOptionView_font_type, 21);
//                setFontType(style);
                String text = a.getString(R.styleable.ShopRuleOptionView_rule_content);
                txtContent.setText(text);
                int textSize = a.getDimensionPixelSize(R.styleable.ShopRuleOptionView_font_size_dp, 10);
                txtContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            if (icon != null) {
                boolean isSelection = a.getBoolean(R.styleable.ShopRuleOptionView_selected, false);
                setIconImage(isSelection);
            }

            if (edtContent != null) {
                int font = a.getInteger(R.styleable.ShopRuleOptionView_edittext_font_type, 21);
//                setEditTextFontType(font);
                int fontSize = a.getDimensionPixelSize(R.styleable.ShopRuleOptionView_edittext_font_size, 10);
                edtContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            }

            isEditTextEnable = a.getBoolean(R.styleable.ShopRuleOptionView_enableEditText, false);

            if (isEditTextEnable) {
                edtContent.setVisibility(VISIBLE);
                txtContent.setVisibility(GONE);

                edtContent.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        rootView.performClick();
                    }
                });
            } else {
                edtContent.setVisibility(GONE);
                txtContent.setVisibility(VISIBLE);
            }
        } finally {
            a.recycle();
        }
    }

    public void setEditTextContent(String data) {
        if (edtContent != null) {
            edtContent.setText(data);
        }
    }

    public void setEditTextEnable(boolean state) {
        if (edtContent != null) {
            edtContent.setEnabled(state);
        }
    }

    public void setEditTextFocus() {
        if (edtContent != null) {
            edtContent.requestFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public void setEdtContentUnFocus() {
        if (edtContent != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtContent.getWindowToken(), 0);
            edtContent.setEnabled(false);
        }
    }

    public void setEditTextVisible(boolean state) {
        isEditTextEnable = state;
        if (state) {
            edtContent.setVisibility(VISIBLE);
            txtContent.setVisibility(GONE);
        } else {
            txtContent.setVisibility(VISIBLE);
            edtContent.setVisibility(GONE);
        }
    }

    public String getEditTextContent() {
        String text = edtContent.getText().toString();
        text = text.trim();
        return text;
    }

    View rootView;
    ImageView icon;
    GhtkTextView txtContent;
    GhtkEditText edtContent;
    private boolean isSelection = false;

    private void init(Context context) {
        rootView = inflate(context, R.layout.layout_shop_note_item, this);
        icon = rootView.findViewById(R.id.layout_shop_note_item_icon);
        txtContent = rootView.findViewById(R.id.layout_shop_note_item_txt_content);
        edtContent = rootView.findViewById(R.id.layout_shop_note_item_edt_text);

        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelection = !isSelection;
                setIconImage(isSelection);

                if (listener != null) {
                    listener.doTouch("touch");
                }
            }
        });

    }

    private void setIconImage(boolean state) {
        if (icon != null) {
            if (state) {
                icon.setImageResource(R.drawable.ic_green_selected_radio);
            } else {
                icon.setImageResource(R.drawable.ic_green_radio);
            }
        }
    }

    public boolean isSelection() {
        return isSelection;
    }

    public void setSelection(boolean state) {
        isSelection = state;
        setIconImage(state);
    }

    public String getTextContent() {
        String result = txtContent.getText().toString();
        result = result.trim();
        return result;
    }

    private void addEditextFont(String fontPath) {
        if (this.edtContent != null) {
            this.edtContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), fontPath));
        }
    }

    public void setEditTextFontType(int type) {
        switch (type) {
            case 13: {
                addEditextFont("fonts/Roboto_Bold.ttf");
                break;
            }
            case 14: {
                addEditextFont("fonts/Roboto_BoldItalic.ttf");
                break;
            }

            case 15: {
                addEditextFont("fonts/Roboto_Condensed.ttf");
                break;
            }

            case 16: {
                addEditextFont("fonts/Roboto_CondensedBold.ttf");
                break;
            }

            case 17: {
                addEditextFont("fonts/Roboto_Italic.ttf");
                break;
            }

            case 18: {
                addEditextFont("fonts/Roboto_Light.ttf");
                break;
            }

            case 19: {
                addEditextFont("fonts/Roboto_LightItalic.ttf");
                break;
            }

            case 20: {
                addEditextFont("fonts/Roboto_Medium.ttf");
                break;
            }

            case 21: {
                addEditextFont("fonts/Roboto_Regular.ttf");
                break;
            }

            case 22: {
                addEditextFont("fonts/ultralight.ttf");
                break;
            }

            case 23: {
                addEditextFont("fonts/ultralightitalic.ttf");
                break;
            }
        }
    }

    private void addFont(String fontPath) {
        if (this.txtContent != null) {
            this.txtContent.setTypeface(Typeface.createFromAsset(getContext().getAssets(), fontPath));
        }
    }

    public void setFontType(int type) {

        switch (type) {
            case 13: {
                addFont("fonts/Roboto_Bold.ttf");
                break;
            }
            case 14: {
                addFont("fonts/Roboto_BoldItalic.ttf");
                break;
            }

            case 15: {
                addFont("fonts/Roboto_Condensed.ttf");
                break;
            }

            case 16: {
                addFont("fonts/Roboto_CondensedBold.ttf");
                break;
            }

            case 17: {
                addFont("fonts/Roboto_Italic.ttf");
                break;
            }

            case 18: {
                addFont("fonts/Roboto_Light.ttf");
                break;
            }

            case 19: {
                addFont("fonts/Roboto_LightItalic.ttf");
                break;
            }

            case 20: {
                addFont("fonts/Roboto_Medium.ttf");
                break;
            }

            case 21: {
                addFont("fonts/Roboto_Regular.ttf");
                break;
            }

            case 22: {
                addFont("fonts/ultralight.ttf");
                break;
            }

            case 23: {
                addFont("fonts/ultralightitalic.ttf");
                break;
            }
        }
    }
}
