package com.ghtk.base.viper.interfaces;

import android.app.Activity;

import androidx.fragment.app.Fragment;

/**
 * Base Presenter
 * Created by neo on 2/5/2016.
 */
public interface IPresenter<V extends IView, I extends IInteractor> {

  void start();

  V getView();

  I onCreateInteractor();

  V onCreateView();

  Fragment getFragment();

  void presentView();

  void pushView();

  void pushView(int frameId);

  Activity getViewContext();

  void registerEventBus();

  void unregisterEventBus();

  void back();

}
