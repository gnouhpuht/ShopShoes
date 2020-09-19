package com.example.shopshoes.screen.homePage;

import com.ghtk.base.viper.Interactor;

/**
 * The HomePageFragment interactor
 */
class HomePageFragmentInteractor extends Interactor<HomePageFragmentContract.Presenter>
        implements HomePageFragmentContract.Interactor {

    HomePageFragmentInteractor(HomePageFragmentContract.Presenter presenter) {
        super(presenter);
    }
}
