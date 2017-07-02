package pl.sggw.stosprzepelniony.viper.user;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.User;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class UserInteractor
        extends BaseRxInteractor
        implements UserContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<User> getUserInfoById(int userdId) {
        return retrofitFactory
                .getUsersAPI()
                .getUserById(userdId)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Ad>> getAdsByUserId(int userId) {
        return retrofitFactory
                .getAdvertisementAPI()
                .getAdsForUserId(userId)
                .subscribeOn(Schedulers.io());
    }
}
