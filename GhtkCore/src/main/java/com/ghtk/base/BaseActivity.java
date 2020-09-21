package com.ghtk.base;

import com.ghtk.utils.ActivityUtils;
import com.ghtk.utils.DialogUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import butterknife.ButterKnife;

/**
 * Base activity
 * Created by neo on 2/5/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    overridePendingTransition(CoreDefault.ANIM_IN, CoreDefault.ANIM_OUT);
    setContentView(getLayoutId());
    // Inject views
    ButterKnife.bind(this);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    // Prepare layout
    initLayout();
  }

  public void initLayout() {
  }

  public void showAlertDialog(String message) {
    DialogUtils.showErrorAlert(this, message);
  }

  public void showProgress() {
    DialogUtils.showProgressDialog(this);
  }

  public void hideProgress() {
    DialogUtils.dismissProgressDialog();
  }

  public void onRequestError(String errorCode, String errorMessage) {
    DialogUtils.showErrorAlert(this, errorMessage);
    hideProgress();
  }

  public void onRequestSuccess() {
    hideProgress();
  }

  /**
   * Return layout resource id for activity
   */
  protected abstract int getLayoutId();

  /**
   * Hide keyboard of current focus view
   */
  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
  }

  /**
   * Hide keyboard of current focus view
   */
  public void hideKeyboard(View view) {
    if (view != null) {
      InputMethodManager imm =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
  }

  /**
   * Show keyboard for {@link EditText}
   */
  public void showKeyboard(EditText editText) {
    editText.requestFocus();
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
  }

  public void addFragment(int containerId, Fragment fragment, boolean addToBackStack,
                          String tag) {
    ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, containerId,
        addToBackStack, tag);
  }

  public void addChildFragment(int containerId, Fragment fragment, boolean addToBackStack,
                          String tag) {
    ActivityUtils.addChildFragment(getSupportFragmentManager(), fragment, containerId,
            addToBackStack, tag);
  }

  public void addFragment(int containerId, BaseFragment fragment, boolean addToBackStack) {
    addFragment(containerId, fragment, addToBackStack, fragment.getClass().getSimpleName());
  }

  public void addFragment(int containerId, BaseFragment fragment) {
    addFragment(containerId, fragment, false, null);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    FragmentManager manager = getSupportFragmentManager();
    if (manager != null) {
      for (Fragment fragment : manager.getFragments()) {
        if (fragment != null) {
          fragment.onActivityResult(requestCode, resultCode, data);
        }
      }
    }
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    boolean handleReturn = super.dispatchTouchEvent(ev);

    View view = getCurrentFocus();

    int x = (int) ev.getX();
    int y = (int) ev.getY();

    if(view instanceof EditText){
      View innerView = getCurrentFocus();

      if (ev.getAction() == MotionEvent.ACTION_UP &&
              !getLocationOnScreen((EditText) innerView).contains(x, y)) {
        //
        //InputMethodManager input = (InputMethodManager)
        //        getSystemService(Context.INPUT_METHOD_SERVICE);
        //input.hideSoftInputFromWindow(getWindow().getCurrentFocus()
        //        .getWindowToken(), 0);
      }
    }

    return handleReturn;
  }

  protected Rect getLocationOnScreen(EditText mEditText) {
    Rect mRect = new Rect();
    int[] location = new int[2];

    mEditText.getLocationOnScreen(location);

    mRect.left = location[0];
    mRect.top = location[1];
    mRect.right = location[0] + mEditText.getWidth();
    mRect.bottom = location[1] + mEditText.getHeight();

    return mRect;
  }
}
