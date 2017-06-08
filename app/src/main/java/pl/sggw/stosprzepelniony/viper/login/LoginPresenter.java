package pl.sggw.stosprzepelniony.viper.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.exception.EmptyPasswordFieldException;
import pl.sggw.stosprzepelniony.exception.IncorrectEmailException;

public class LoginPresenter
        extends BaseRxPresenter
        <LoginContract.View,
                LoginContract.Interactor,
                LoginContract.Routing>
        implements ViperPresenter<LoginContract.View> {

    @Override
    public void attachView(LoginContract.View view) {
        super.attachView(view);
        if (isViewAttached()) {
            addSubscription(
                    getView()
                            .getLoginClicks()
                            .doOnNext(loginBundle -> getView().showLoading())
                            .doOnNext(this::validateLoginCredentials)
                            .flatMap(getInteractor()::performLogin)
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError(throwable -> getView().showError(throwable))
                            .retry()
                            .subscribe(loginBundle -> getRouting().startMainActivity()));
            addSubscription(
                    getView()
                            .getForgottenPasswordClicks()
                            .subscribe(object -> getRouting().startResetPasswordActivity(),
                                    throwable -> getView().showError(throwable)));
            addSubscription(
                    getView()
                            .getSignUpClicks()
                            .subscribe(object -> getRouting().startSignUpActivity(),
                                    throwable -> getView().showError(throwable)));
        }
    }

    private void validateLoginCredentials(LoginBundle loginBundle) throws IncorrectEmailException, EmptyPasswordFieldException {
        if (TextUtils.isEmpty(loginBundle.getEmail()) ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(loginBundle.getEmail()).matches())
            throw new IncorrectEmailException();
        if (TextUtils.isEmpty(loginBundle.getPassword()))
            throw new EmptyPasswordFieldException();
    }

    @NonNull
    @Override
    public LoginContract.Routing createRouting() {
        return new LoginRouting();
    }

    @NonNull
    @Override
    public LoginContract.Interactor createInteractor() {
        return new LoginInteractor();
    }
}
