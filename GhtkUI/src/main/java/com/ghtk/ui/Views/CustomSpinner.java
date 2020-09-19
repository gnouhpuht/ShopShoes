package com.ghtk.ui.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

@SuppressLint("AppCompatCustomView")
public class CustomSpinner extends Spinner {

    public Boolean isUserSelect = false;// = false khi fillData, = true khi người dùng click vào item

    public CustomSpinner(Context context) {
        super(context);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSelection(int position, boolean animate) {
        isUserSelect = true;
        int oldPosition = getSelectedItemPosition();
        boolean sameSelected = position == oldPosition;
        if (sameSelected) {
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        } else {
            super.setSelection(position, animate);
        }
    }

    @Override
    public void setSelection(int position) {
        isUserSelect = true;
        int oldPosition = getSelectedItemPosition();
        boolean sameSelected = position == oldPosition;
        if (sameSelected) {
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        } else {
            super.setSelection(position);
        }
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
