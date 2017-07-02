package pl.sggw.stosprzepelniony.viper.new_advertisement;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.exception.EmptySubjectFieldException;
import pl.sggw.stosprzepelniony.exception.IncorrectSalaryValueException;
import pl.sggw.stosprzepelniony.exception.NoCategoryException;
import pl.sggw.stosprzepelniony.exception.TooShortAdDescriptionException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static pl.sggw.stosprzepelniony.util.ObservableExtensions.withObservableRetryErrorLogic;

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
                        .observeOn(Schedulers.io())
                        .flatMapSingle(event -> getInteractor().performAddAdvertisement(event).toSingleDefault(event))
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(withObservableRetryErrorLogic(error -> getView().showError(error)))
                        .subscribe(event -> {
                            getView().showConfirmationInfo();
                            getRouting().closeScreen();
                        }));
        addSubscription(
                getView()
                        .getDismissButtonClicks()
                        .filter(event -> isViewAttached())
                        .compose(withObservableRetryErrorLogic(error -> getView().showError(error)))
                        .subscribe(event -> getRouting().closeScreen()));
        addSubscription(
                getView()
                        .getCategoriesClicks()
                        .filter(event -> isViewAttached())
                        .compose(withObservableRetryErrorLogic(error -> getView().showError(error)))
                        .subscribe(event -> getView().showCategoryChoosingDialog()));

        addSubscription(
                getInteractor()
                        .getCategories()
                        .subscribe(getView()::setCategories, getView()::showError)
        );
    }

    private void validateAdvertisement(NewAdvertisementBundle advertisement) throws Exception {
        if (TextUtils.isEmpty(advertisement.getSubject()))
            throw new EmptySubjectFieldException();
        if (advertisement.getSalary() == 0)
            throw new IncorrectSalaryValueException();
        if (advertisement.getContent().length() <= 20)
            throw new TooShortAdDescriptionException();
        if (advertisement.getCategoryId() == NewAdvertisementContract.View.NO_CATEGORY)
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
