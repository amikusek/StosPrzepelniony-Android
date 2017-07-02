package pl.sggw.stosprzepelniony.viper.splash;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;
import pl.sggw.stosprzepelniony.di.DIProvider;

import io.reactivex.Completable;
import io.reactivex.Observable;

class SplashInteractor
        extends BaseRxInteractor
        implements SplashContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable checkUserSessionStatus() {
        return retrofitFactory
                .getUsersAPI()
                .checkUserSessionStatus();
    }

    @Override
    public Boolean userHasEmptySessionToken() {
        return DIProvider
                .getInstance()
                .getPersistentStorage()
                .getSessionToken()
                .isEmpty();
    }

    @Override
    public Observable<Boolean> isAutoSignInEnabled() {
        return Observable.just(
                DIProvider.getInstance()
                        .getPersistentStorage()
                        .isAutoSignInEnabled());
    }
}
