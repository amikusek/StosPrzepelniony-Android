package pl.sggw.stosprzepelniony.viper.splash;

import android.support.annotation.NonNull;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.exception.MissingSessionTokenException;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class SplashPresenter
        extends BaseRxPresenter
        <SplashContract.View,
                SplashContract.Interactor,
                SplashContract.Routing>
        implements ViperPresenter<SplashContract.View> {

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);

        addSubscription(
                Single
                        .timer(3, TimeUnit.SECONDS)
                        .doOnSuccess(event -> {
                            if (getInteractor().userHasEmptySessionToken())
                                throw new MissingSessionTokenException();
                        })
                        .flatMap(event -> getInteractor().checkUserSessionStatus().toSingleDefault(event))
                        .flatMapObservable(event -> getInteractor().isAutoSignInEnabled())
                        .subscribe(
                                isAutoSignInEnabled -> {
                                    if (isAutoSignInEnabled) getRouting().startMainScreen();
                                    else getRouting().startLoginScreen();
                                },
                                error -> getRouting().startLoginScreen()));
    }

    @NonNull
    @Override
    public SplashContract.Routing createRouting() {
        return new SplashRouting();
    }

    @NonNull
    @Override
    public SplashContract.Interactor createInteractor() {
        return new SplashInteractor();
    }
}
