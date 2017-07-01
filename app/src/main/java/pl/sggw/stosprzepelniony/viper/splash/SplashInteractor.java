package pl.sggw.stosprzepelniony.viper.splash;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.Category;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;
import pl.sggw.stosprzepelniony.di.DIProvider;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
    public Observable<List<Category>> getCategories() {
        return retrofitFactory
                .getCategoriesAPI()
                .getCategories()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> isAutoSignInEnabled() {
        return Observable.just(
                DIProvider.getInstance()
                        .getPersistentStorage()
                        .isAutoSignInEnabled());
    }
}
