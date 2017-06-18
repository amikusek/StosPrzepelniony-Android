package pl.sggw.stosprzepelniony.viper.reset_password;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import pl.sggw.stosprzepelniony.data.entity.ResetPasswordBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

class ResetPasswordInteractor
        extends BaseRxInteractor
        implements ResetPasswordContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable performPasswordRestoring(ResetPasswordBundle resetPasswordBundle) {
        return retrofitFactory
                .getResetPasswordAPI()
                .performPasswordRestoring(resetPasswordBundle.getEmail())
                .subscribeOn(Schedulers.io());
    }
}
