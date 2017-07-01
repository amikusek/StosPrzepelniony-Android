package pl.sggw.stosprzepelniony.viper.new_advertisement;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import io.reactivex.Completable;

class NewAdvertisementInteractor
        extends BaseRxInteractor
        implements NewAdvertisementContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable performAddAdvertisement(NewAdvertisementBundle advertisement) {
        return retrofitFactory
                .getAddAdvertisementAPI()
                .performAddAdvertisement(advertisement);
    }
}
