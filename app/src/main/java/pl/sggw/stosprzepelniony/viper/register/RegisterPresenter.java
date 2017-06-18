package pl.sggw.stosprzepelniony.viper.register;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.sggw.stosprzepelniony.data.entity.RegisterBundle;
import pl.sggw.stosprzepelniony.exception.EmptyFirstNameFieldException;
import pl.sggw.stosprzepelniony.exception.EmptyLastNameFieldException;
import pl.sggw.stosprzepelniony.exception.EmptyPasswordFieldException;
import pl.sggw.stosprzepelniony.exception.IncorrectEmailException;
import pl.sggw.stosprzepelniony.exception.PasswordsNotIdenticalException;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withCompletableRetryErrorLogic;

public class RegisterPresenter
        extends BaseRxPresenter
        <RegisterContract.View,
                RegisterContract.Interactor,
                RegisterContract.Routing>
        implements RegisterContract.Presenter {

    @Override
    public void attachView(RegisterContract.View view) {
        super.attachView(view);
        addSubscription(
                getView()
                        .getRegisterClicks()
                        .filter(event -> isViewAttached())
                        .doOnNext(registerBundle -> getView().showLoading())
                        .doOnNext(this::validateRegisterCredentials)
                        .flatMapCompletable(getInteractor()::performRegistration)
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withCompletableRetryErrorLogic(error -> getView().showError(error)))
                        .subscribe(() -> {
                            getView().showRegistrationSuccess();
                            getRouting().closeScreen();
                        }));
        addSubscription(
                getView()
                        .getCloseActivityClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(event -> getRouting().closeScreen()));
    }

    private void validateRegisterCredentials(RegisterBundle registerBundle) throws
            IncorrectEmailException, EmptyFirstNameFieldException,
            EmptyLastNameFieldException, EmptyPasswordFieldException,
            PasswordsNotIdenticalException {

        if (TextUtils.isEmpty(registerBundle.getEmail()) ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(registerBundle.getEmail()).matches())
            throw new IncorrectEmailException();

        if (TextUtils.isEmpty(registerBundle.getFirstName()))
            throw new EmptyFirstNameFieldException();

        if (TextUtils.isEmpty(registerBundle.getLastName()))
            throw new EmptyLastNameFieldException();

        if (TextUtils.isEmpty(registerBundle.getPassword()) ||
                TextUtils.isEmpty(registerBundle.getPasswordConfirmation()))
            throw new EmptyPasswordFieldException();

        if (!TextUtils.equals(registerBundle.getPassword(), registerBundle.getPasswordConfirmation()))
            throw new PasswordsNotIdenticalException();
    }

    @NonNull
    @Override
    public RegisterContract.Routing createRouting() {
        return new RegisterRouting();
    }

    @NonNull
    @Override
    public RegisterContract.Interactor createInteractor() {
        return new RegisterInteractor();
    }
}
