package pl.sggw.stosprzepelniony.viper.register;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.RegisterBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

class RegisterInteractor
        extends BaseRxInteractor
        implements RegisterContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable performRegistration(RegisterBundle registerBundle) {
        return retrofitFactory
                .getRegisterApi()
                .performRegistration(registerBundle.withHashedPassword())
                .subscribeOn(Schedulers.io());
    }
}
