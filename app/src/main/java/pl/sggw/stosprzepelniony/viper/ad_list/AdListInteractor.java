package pl.sggw.stosprzepelniony.viper.ad_list;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.AdsFilter;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class AdListInteractor
        extends BaseRxInteractor
        implements AdListContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<List<Ad>> getAdsWithFilter(AdsFilter adsFilter) {
        return retrofitFactory
                .getAdvertisementAPI()
                .getAds()
                .subscribeOn(Schedulers.io());
/*        //We are creating dummy objects here because remote API is still not ready yet.
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(1, new User(), 3, "To jest tytul1", "To jest content1", 5000f, 50f, new Date()));
        ads.add(new Ad(2, new User(), 1, "To jest tytul2", "To jest content2", 0, 40f, new Date()));
        ads.add(new Ad(3, new User(), 9, "To jest tytul3", "To jest content3", 25000f, 50f, new Date()));
        ads.add(new Ad(4, new User(), 3, "To jest tytul4", "To jest content4", 18500f, 50f, new Date()));
        ads.add(new Ad(5, new User(), 6, "To jest tytul5", "To jest content5", 0, 30f, new Date()));
        ads.add(new Ad(6, new User(), 4, "To jest tytul6", "To jest content6", 15000f, 50f, new Date()));
        ads.add(new Ad(7, new User(), 5, "To jest tytul7", "To jest content7", 7500f, 50f, new Date()));
        ads.add(new Ad(8, new User(), 7, "To jest tytul8", "To jest content8", 50f, 50f, new Date()));
        ads.add(new Ad(9, new User(), 10, "To jest tytul9", "To jest content9", 50f, 50f, new Date()));
        ads.add(new Ad(10, new User(), 6, "To jest tytul10", "To jest content10", 50f, 50f, new Date()));
        ads.add(new Ad(10, new User(), 9, "To jest tytul11", "To jest content11", 50f, 50f, new Date()));
        return Observable.just(ads);*/
    }
}
