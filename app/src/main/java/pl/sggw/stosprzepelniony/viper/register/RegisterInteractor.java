package pl.sggw.stosprzepelniony.viper.register;

import com.google.common.hash.Hashing;
import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.nio.charset.Charset;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import pl.sggw.stosprzepelniony.data.entity.RegisterBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

class RegisterInteractor
        extends BaseRxInteractor
        implements RegisterContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable performRegistration(RegisterBundle registerBundle) {
        return retrofitFactory
                .getRegisterApi()
                .performRegistration(registerBundle.getEmail(),
                        registerBundle.getFirstName(),
                        registerBundle.getLastName(),
                        Hashing.sha256()
                                .hashString(registerBundle.getPassword(), Charset.forName("UTF-8"))
                                .toString())
                .subscribeOn(Schedulers.io());
    }
}
