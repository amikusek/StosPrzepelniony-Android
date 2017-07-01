package pl.sggw.stosprzepelniony.viper.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.exception.EmptyPasswordFieldException;
import pl.sggw.stosprzepelniony.exception.IncorrectEmailException;

import io.reactivex.android.schedulers.AndroidSchedulers;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withObservableRetryErrorLogic;

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
                            .compose(withObservableRetryErrorLogic(throwable -> getView().showError(throwable)))
                            .subscribe(loginBundle -> getRouting().startMainActivity()));
            addSubscription(
                    getView()
                            .getForgottenPasswordClicks()
                            .compose(withObservableRetryErrorLogic(throwable -> getView().showError(throwable)))
                            .subscribe(object -> getRouting().startResetPasswordActivity()));
            addSubscription(
                    getView()
                            .getSignUpClicks()
                            .compose(withObservableRetryErrorLogic(throwable -> getView().showError(throwable)))
                            .subscribe(object -> getRouting().startSignUpActivity()));
            addSubscription(
                    getView()
                            .getAutoSignInClicks()
                            .compose(withObservableRetryErrorLogic(throwable -> getView().showError(throwable)))
                            .subscribe(getInteractor()::saveSingInMode));
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
