package pl.sggw.stosprzepelniony.viper.login;

import com.google.common.hash.Hashing;
import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.nio.charset.Charset;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;
import pl.sggw.stosprzepelniony.data.result.LoginResult;

class LoginInteractor
        extends BaseRxInteractor
        implements LoginContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<LoginResult> performLogin(LoginBundle loginBundle) {
        return retrofitFactory
                .getLoginApi()
                .performLogin(loginBundle.getEmail(),
                        Hashing.sha256()
                                .hashString(loginBundle.getPassword(), Charset.forName("UTF-8"))
                                .toString())
                .subscribeOn(Schedulers.io());
    }
}
