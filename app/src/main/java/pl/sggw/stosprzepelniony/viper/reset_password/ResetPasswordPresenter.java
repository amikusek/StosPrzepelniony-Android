package pl.sggw.stosprzepelniony.viper.reset_password;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.sggw.stosprzepelniony.data.entity.ResetPasswordBundle;
import pl.sggw.stosprzepelniony.exception.IncorrectEmailException;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withCompletableRetryErrorLogic;

public class ResetPasswordPresenter
        extends BaseRxPresenter
        <ResetPasswordContract.View,
                ResetPasswordContract.Interactor,
                ResetPasswordContract.Routing>
        implements ViperPresenter<ResetPasswordContract.View> {

    @Override
    public void attachView(ResetPasswordContract.View view) {
        super.attachView(view);
        addSubscription(
                getView()
                        .getResetPasswordClicks()
                        .filter(event -> isViewAttached())
                        .doOnNext(registerBundle -> getView().showLoading())
                        .doOnNext(this::validateEmail)
                        .flatMapCompletable(getInteractor()::performPasswordRestoring)
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withCompletableRetryErrorLogic(error -> getView().showError(error)))
                        .subscribe(() -> {
                            getView().showResetPasswordSuccess();
                            getRouting().closeScreen();
                        }));
        addSubscription(
                getView()
                        .getCloseActivityClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(event -> getRouting().closeScreen()));
    }

    private void validateEmail(ResetPasswordBundle resetPasswordBundle) throws IncorrectEmailException {
        if (TextUtils.isEmpty(resetPasswordBundle.getEmail()) ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(resetPasswordBundle.getEmail()).matches())
            throw new IncorrectEmailException();
    }

    @NonNull
    @Override
    public ResetPasswordContract.Routing createRouting() {
        return new ResetPasswordRouting();
    }

    @NonNull
    @Override
    public ResetPasswordContract.Interactor createInteractor() {
        return new ResetPasswordInteractor();
    }
}
