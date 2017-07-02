package pl.sggw.stosprzepelniony.viper.ad_details;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class AdDetailsInteractor
        extends BaseRxInteractor
        implements AdDetailsContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<Ad> getAdById(int adId) {
        // We are creating dummy object here because remote API is still not ready yet.
        return retrofitFactory
                .getAdvertisementAPI()
                .getAdById(adId)
                .subscribeOn(Schedulers.io());
    }
}
