package com.example.shopshoes.screen.homePage;


import com.ghtk.base.viper.interfaces.IInteractor;
import com.ghtk.base.viper.interfaces.IPresenter;
import com.ghtk.base.viper.interfaces.PresentView;

/**
 * The HomePageFragment Contract
 */
interface HomePageFragmentContract {

    interface Interactor extends IInteractor<Presenter> {
    }

    interface View extends PresentView<Presenter> {
    }

    interface Presenter extends IPresenter<View, Interactor> {
    }
}



