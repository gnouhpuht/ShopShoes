package com.ghtk.base.viper;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.ghtk.base.viper.interfaces.ContainerView;
import com.ghtk.base.viper.interfaces.IInteractor;
import com.ghtk.base.viper.interfaces.IPresenter;
import com.ghtk.base.viper.interfaces.IView;
import com.ghtk.eventbus.EventBusWrapper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Base implements for presenters
 * Created by neo on 14/03/2016.
 */
public abstract class Presenter<V extends IView, I extends IInteractor>
        implements IPresenter<V, I> {
    protected ContainerView mContainerView;
    protected V mView;
    protected I mInteractor;

    @SuppressWarnings("unchecked")
    public Presenter(ContainerView containerView) {
        mContainerView = containerView;
        mInteractor = onCreateInteractor();
        mView = onCreateView();

        mView.setPresenter(this);
    }




    public List<Fragment> getListFragmentChill(){
        return getFragment().getFragmentManager().getFragments();
    }

    public HashMap<String,Fragment> getKeyMapFragmentChill(){
        HashMap<String,Fragment> fragmentHashMap = new HashMap<>();
        for (Fragment fragment: getListFragmentChill()){
            fragmentHashMap.put(fragment.getClass().getSimpleName(),fragment);
        }
        return fragmentHashMap;
    }

    public Activity getViewContext() {
        return mView.getViewContext();
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public Fragment getFragment() {
        return getView() instanceof Fragment ? (Fragment) getView() : null;
    }

    @Override
    public void presentView() {
        mContainerView.presentView(mView);
    }

    @Override
    public void pushView() {
        mContainerView.pushView(mView);
    }

    @Override
    public void pushView(int frameId) {
        mContainerView.pushView(mView, frameId);
    }

    public void addView() {
        mContainerView.addView(mView);
    }

    // Event bus

    @Override
    public void registerEventBus() {
        EventBusWrapper.register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBusWrapper.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(NoneEvent event) {
    }


    public static class NoneEvent {
    }

    @Override
    public void back() {
//    mContainerView.getViewContext().onBackPressed();
        mContainerView.back();
    }
}
