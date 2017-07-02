package pl.sggw.stosprzepelniony.viper.inbox;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.MessageListItem;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class InboxInteractor
        extends BaseRxInteractor
        implements InboxContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<List<MessageListItem>> getMessages() {
        return retrofitFactory
                .getMessagesAPI()
                .getConversations()
                .subscribeOn(Schedulers.io());
    }
}
