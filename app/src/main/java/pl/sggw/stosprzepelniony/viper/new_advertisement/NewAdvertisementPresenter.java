package pl.sggw.stosprzepelniony.viper.new_advertisement;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.exception.EmptySubjectFieldException;
import pl.sggw.stosprzepelniony.exception.IncorrectSalaryValueException;
import pl.sggw.stosprzepelniony.exception.NoCategoryException;
import pl.sggw.stosprzepelniony.exception.NoSalaryTypeException;
import pl.sggw.stosprzepelniony.exception.TooShortAdDescriptionException;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withCompletableRetryErrorLogic;

public class NewAdvertisementPresenter
        extends BaseRxPresenter
        <NewAdvertisementContract.View,
                NewAdvertisementContract.Interactor,
                NewAdvertisementContract.Routing>
        implements ViperPresenter<NewAdvertisementContract.View> {

    @Override
    public void attachView(NewAdvertisementContract.View view) {
        super.attachView(view);
        addSubscription(
                getView()
                        .getAddButtonClicks()
                        .filter(event -> isViewAttached())
                        .doOnNext(registerBundle -> getView().showLoading())
                        .doOnNext(this::validateAdvertisement)
                        .flatMapCompletable(getInteractor()::performAddAdvertisement)
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withCompletableRetryErrorLogic(error -> getView().showError(error)))
                        .subscribe(() -> {
                            getView().showConfirmationInfo();
                            getRouting().closeScreen();
                        }));
        addSubscription(
                getView()
                        .getDismissButtonClicks()
                        .filter(event -> isViewAttached())
                        .subscribe(event -> getRouting().closeScreen()));
    }

    private void validateAdvertisement(NewAdvertisementBundle advertisement) throws Exception {
        if (TextUtils.isEmpty(advertisement.getSubject()))
            throw new EmptySubjectFieldException();
        if (advertisement.getSalary() == 0)
            throw new IncorrectSalaryValueException();
        if (advertisement.getSalaryType() == -1)
            throw new NoSalaryTypeException();
        if (advertisement.getDescription().length() <= 20)
            throw new TooShortAdDescriptionException();
        if (advertisement.getCategoryId() == -1)
            throw new NoCategoryException();
    }

    @NonNull
    @Override
    public NewAdvertisementContract.Routing createRouting() {
        return new NewAdvertisementRouting();
    }

    @NonNull
    @Override
    public NewAdvertisementContract.Interactor createInteractor() {
        return new NewAdvertisementInteractor();
    }
}
