package com.ghtk.ui.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by Admin on 2/1/2018.
 */

@SuppressLint("AppCompatCustomView")
public class GhtkSpinner extends Spinner {

    public Boolean isUserSelect = false;// = false khi fillData, = true khi người dùng click vào item

    private int currentPosition = -1;

    public GhtkSpinner(Context context) {
        super(context);
    }

    public GhtkSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GhtkSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSelection(int position) {
        isUserSelect = true;
        boolean sameSelected = position == currentPosition;
        if (!sameSelected) {
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        } else {
            super.setSelection(position);
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public interface OnSpinnerEventsListener {
        void onSpinnerOpened(Spinner spinner);

        void onSpinnerClosed(Spinner spinner);
    }

    private OnSpinnerEventsListener mListener;
    private boolean mOpenInitiated = false;

    @Override
    public boolean performClick() {
        mOpenInitiated = true;
        if (mListener != null) {
            mListener.onSpinnerOpened(this);
        }
        return super.performClick();
    }

    public void setSpinnerEventsListener(OnSpinnerEventsListener eventsListener) {
        mListener = eventsListener;
    }

    public void performClosedEvent() {
        mOpenInitiated = false;
        if (mListener != null) {
            mListener.onSpinnerClosed(this);
        }
    }

    public boolean hasBeenOpened() {
        return mOpenInitiated;
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasBeenOpened() && hasFocus) {
            performClosedEvent();
        }
    }
}

