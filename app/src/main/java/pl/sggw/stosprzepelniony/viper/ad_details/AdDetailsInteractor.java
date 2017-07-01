package pl.sggw.stosprzepelniony.viper.ad_details;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.User;

import java.util.Date;

import io.reactivex.Observable;

class AdDetailsInteractor
        extends BaseRxInteractor
        implements AdDetailsContract.Interactor {

    @Override
    public Observable<Ad> getAdById(int adId) {
        // We are creating dummy object here because remote API is still not ready yet.
        return Observable.just(new Ad(3, new User(), 7, "To jest tytul1", "To jest content1", 5000f, 50f, new Date()));
    }
}
