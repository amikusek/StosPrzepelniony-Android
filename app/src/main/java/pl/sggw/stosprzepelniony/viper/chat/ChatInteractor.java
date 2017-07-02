package pl.sggw.stosprzepelniony.viper.chat;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.ChatMessageBundle;
import pl.sggw.stosprzepelniony.data.entity.Message;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class ChatInteractor
        extends BaseRxInteractor
        implements ChatContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<List<Message>> getMessagesFromUserById(int adId, int senderId) {
        return retrofitFactory
                .getMessagesAPI()
                .getMessagesForUser(adId, senderId)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable sendMessage(ChatMessageBundle bundle) {
        return retrofitFactory
                .getMessagesAPI()
                .sendMessage(bundle.getAdId(), bundle.getUserIdSender(), bundle)
                .subscribeOn(Schedulers.io());
    }
}
