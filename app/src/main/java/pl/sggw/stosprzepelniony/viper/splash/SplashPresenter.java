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
                        .timer(2, TimeUnit.SECONDS)
                        .doOnSuccess(event -> {
                            if (getInteractor().userHasEmptySessionToken())
                                throw new MissingSessionTokenException();
                        })
                        .flatMapCompletable(event -> getInteractor().checkUserSessionStatus())
                        .subscribe(
                                () -> getRouting().startMainScreen(),
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
