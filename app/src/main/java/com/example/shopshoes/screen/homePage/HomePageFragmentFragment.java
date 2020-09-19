package com.example.shopshoes.screen.homePage;

import com.example.shopshoes.R;
import com.ghtk.base.viper.ViewFragment;

/**
 * The HomePageFragment Fragment
 */
public class HomePageFragmentFragment extends ViewFragment<HomePageFragmentContract.Presenter> implements HomePageFragmentContract.View {

    public static HomePageFragmentFragment getInstance() {
        return new HomePageFragmentFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
}
