package com.example.shopshoes.screen.homePage;


import com.ghtk.base.viper.Presenter;
import com.ghtk.base.viper.interfaces.ContainerView;

/**
 * The HomePageFragment Presenter
 */
public class HomePageFragmentPresenter extends Presenter<HomePageFragmentContract.View, HomePageFragmentContract.Interactor>
        implements HomePageFragmentContract.Presenter {

    public HomePageFragmentPresenter(ContainerView containerView) {
        super(containerView);
    }

    @Override
    public HomePageFragmentContract.View onCreateView() {
        return HomePageFragmentFragment.getInstance();
    }

    @Override
    public void start() {
        // Start getting data here
    }

    @Override
    public HomePageFragmentContract.Interactor onCreateInteractor() {
        return new HomePageFragmentInteractor(this);
    }
}
