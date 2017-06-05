package pl.sggw.stosprzepelniony.viper.login;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.data.result.LoginResult;

class LoginInteractor
        extends BaseRxInteractor
        implements LoginContract.Interactor {

    @Override
    public Observable<LoginResult> performLogin(LoginBundle loginBundle) {
        return null;
    }
}
