package pl.sggw.stosprzepelniony.viper.new_advertisement;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.data.entity.SingleCategory;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class NewAdvertisementInteractor
        extends BaseRxInteractor
        implements NewAdvertisementContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable performAddAdvertisement(NewAdvertisementBundle advertisement) {
        return retrofitFactory
                .getAdvertisementAPI()
                .performAddAdvertisement(advertisement)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<SingleCategory>> getCategories() {
        return retrofitFactory
                .getCategoriesAPI()
                .getCategories()
                .subscribeOn(Schedulers.io());
    }
}
