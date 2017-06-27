package pl.sggw.stosprzepelniony.viper.login;

import android.util.Base64;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;
import pl.sggw.stosprzepelniony.data.result.LoginResult;
import pl.sggw.stosprzepelniony.di.DIProvider;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class LoginInteractor
        extends BaseRxInteractor
        implements LoginContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<LoginResult> performLogin(LoginBundle loginBundle) {
        return retrofitFactory
                .getLoginApi()
                .performLogin(loginBundle.withHashedPassword())
                .subscribeOn(Schedulers.io())
                .doOnNext(result ->
                        DIProvider
                                .getInstance()
                                .getPersistentStorage()
                                .saveSessionToken(generateSessionToken(result)));
    }

    private String generateSessionToken(LoginResult loginResult) {
        return Base64.encodeToString(
                String.format("%d:%s", loginResult.getUserId(), loginResult.getSessionToken()).getBytes(),
                Base64.DEFAULT
        );
    }
}
