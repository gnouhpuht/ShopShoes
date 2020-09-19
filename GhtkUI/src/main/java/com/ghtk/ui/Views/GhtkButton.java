package com.ghtk.ui.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.ghtk.ui.R;

/**
 * Created by Admin on 1/16/2018.
 */

public class GhtkButton extends android.support.v7.widget.AppCompatButton {

    public static int BOLD = 1;
    public static int BoldItalic = 2;
    public static int Medium = 3;
    public static int MediumItalic = 4;
    public static int Regular = 5;
    public static int RegularItalic = 6;
    public static int Thin = 7;
    public static int ThinItalic = 8;

    public GhtkButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initConfig(context, attrs);
    }

    public int currentFont = 10;

    public void setFontForText(int Type) {

        switch (Type) {

            case 1: {
                currentFont = 1;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Bold.ttf"));
                break;
            }
            case 2: {
                currentFont = 2;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_BoldItalic.ttf"));
                break;
            }
            case 3: {
                currentFont = 3;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Medium.ttf"));
                break;
            }
            case 4: {
                currentFont = 4;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_MediumItalic.ttf"));
                break;
            }
            case 5: {
                currentFont = 5;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
                break;
            }
            case 6: {
                currentFont = 6;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Italic.ttf"));
                break;
            }
            case 7: {
                currentFont = 7;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Light.ttf"));
                break;
            }
            case 8: {
                currentFont = 8;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_LightItalic.ttf"));
                break;
            }
            case 9: {
                currentFont = 9;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Condensed.ttf"));
                break;
            }
            case 10: {
                currentFont = 10;
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_CondensedItalic.ttf"));
                break;
            }
            default:
                super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto_Regular.ttf"));
                break;
        }
    }

    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GhtkButton, 0, 0);
        try {
            int style = a.getInteger(R.styleable.GhtkButton_font_text_button, 5);
            setFontForText(style);
        } finally {
            a.recycle();
        }
    }
}
