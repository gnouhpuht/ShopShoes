package com.ghtk.base.viper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.ghtk.base.BaseActivity;
import com.ghtk.base.BaseFragment;
import com.ghtk.base.viper.interfaces.IPresenter;
import com.ghtk.base.viper.interfaces.PresentView;
import com.ghtk.common.R;
import com.ghtk.utils.ContextUtils;
import com.ghtk.utils.DialogUtils;
import com.ghtk.utils.NetworkUtils;

import butterknife.ButterKnife;

/**
 * Fragments that stand for View
 * Created by neo on 9/15/2016.
 */
public abstract class ViewFragment<P extends IPresenter>
    extends BaseFragment implements PresentView<P> {
  protected P mPresenter;
  protected boolean mIsInitialized = false;
  protected Bundle savedInstanceState;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    this.savedInstanceState=savedInstanceState;
    if (!mIsInitialized) {
      mRootView = super.onCreateView(inflater, container, savedInstanceState);
      // Prepare layout
      if (getArguments() != null) {
        parseArgs(getArguments());
      }
      // Inject views
      ButterKnife.bind(this, mRootView);

      mIsInitialized = true;
    }


    return mRootView;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initLayout();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (!mStartOnAnimationEnded && !mIsStarted) {
      startPresent();
    }
  }

  @Override
  protected void startPresent() {
    mPresenter.start();
    mIsStarted = true;
  }

  @Override
  public void showProgress() {
    if (ContextUtils.isValidContext(getBaseActivity())) {
      getBaseActivity().showProgress();
    }
  }

  @Override
  public void hideProgress() {
    if (ContextUtils.isValidContext(getBaseActivity())) {
      getBaseActivity().hideKeyboard();
      getBaseActivity().hideProgress();
    }
  }

  @Override
  public void initLayout() {
    // Override this method when need to preview some views, layouts
  }

  @Override
  public void showAlertDialog(String message) {
    if (ContextUtils.isValidContext(getBaseActivity())) {
      getBaseActivity().showAlertDialog(message);
    }
  }

  @Override
  public BaseActivity getBaseActivity() {
    if (getActivity() instanceof BaseActivity) {
      return (BaseActivity) getActivity();
    } else {
      return null;
    }
  }

  @Override
  public void onRequestError(String errorCode, String errorMessage) {
    if (ContextUtils.isValidContext(getBaseActivity())) {
      getBaseActivity().onRequestError(errorCode, errorMessage);
    }
  }

  @Override
  public void onNetworkError(boolean shouldShowPopup) {
    if (!NetworkUtils.isNoNetworkAvailable(getActivity(), shouldShowPopup)) {
      DialogUtils.showErrorAlert(getActivity(), getString(R.string.msg_network_lost));
    }
  }

  @Override
  public void onRequestSuccess() {
    if (ContextUtils.isValidContext(getBaseActivity())) {
      getBaseActivity().onRequestSuccess();
    }
  }

  @Override
  public Activity getViewContext() {
    return getActivity();
  }

  @Override
  public void setPresenter(P presenter) {
    mPresenter = presenter;
  }

  /**
   * Parse Arguments that sent to this fragment
   * Override if needed
   *
   * @param args sent to this fragment
   */
  protected void parseArgs(Bundle args) {
  }

  @Override
  protected boolean needTranslationAnimation() {
    return true;
  }

  protected AlertDialog.Builder newDialogBuilder(String title, String message) {
    try {
      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

      builder.setTitle(title);
      builder.setMessage(message);
      builder.setNegativeButton("Đóng", (dialog, id) -> {

      });

      return builder;
    } catch (Exception e) {
      return null;
    }
  }

  public void runActivityForResult(Class clazz, Bundle bundle, int requestCode) {
    Intent intent = new Intent(getContext(), clazz);
    intent.putExtras(bundle);
    startActivityForResult(intent, requestCode);
  }
  public void showDialogFragment(DialogFragment dialogFragment) {
    Log.e("@@@@@", "Show Dialog");
    dialogFragment.show(getFragmentManager(), dialogFragment.getClass().toString());
  }
  public void showFullScreenFragment(final DialogFragment dialogFragment) {
    dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    if (getBaseActivity() != null) {
      getBaseActivity().runOnUiThread(new Runnable() {
        @Override
        public void run() {
          FragmentTransaction transaction = getBaseActivity().getSupportFragmentManager().beginTransaction();
          transaction.add(dialogFragment, dialogFragment.getClass().toString());
          transaction.commitAllowingStateLoss();
        }
      });
    }
  }

}
