package pl.sggw.stosprzepelniony.viper.new_advertisement;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import io.reactivex.Completable;
import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

class NewAdvertisementInteractor
        extends BaseRxInteractor
        implements NewAdvertisementContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Completable performAddAdvertisement(NewAdvertisementBundle advertisement) {
        return retrofitFactory
                .getAddAdvertisementAPI()
                .performAddAdvertisement(advertisement.getCategoryId(),
                        advertisement.getSubject(),
                        advertisement.getDescription(),
                        (advertisement.getSalaryType() == 1 ? advertisement.getSalary() : 0),
                        (advertisement.getSalaryType() == 2) ? advertisement.getSalary() : 0);
    }
}
