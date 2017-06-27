package pl.sggw.stosprzepelniony.viper.user;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.User;

class UserInteractor
        extends BaseRxInteractor
        implements UserContract.Interactor {

    @Override
    public Observable<User> getUserInfoById(int userdId) {
        //We are creating dummy objects here because remote API is still not ready yet.
        return Observable.just(new User("John", "Doe", "johndoe@mail.com"));
    }

    @Override
    public Observable<List<Ad>> getAdsByUserId(int userId) {
        //We are creating dummy objects here because remote API is still not ready yet.
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(3, new User(), 4, "To jest tytul1", "To jest content1", 5000f, 50f, new Date()));
        ads.add(new Ad(3, new User(), 4, "To jest tytul2", "To jest content2", 0, 40f, new Date()));
        return Observable.just(ads);
    }
}
