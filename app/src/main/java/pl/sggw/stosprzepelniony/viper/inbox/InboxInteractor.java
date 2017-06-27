package pl.sggw.stosprzepelniony.viper.inbox;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;

class InboxInteractor
        extends BaseRxInteractor
        implements InboxContract.Interactor {

    @Override
    public Observable<List<MessageListItem>> getMessages() {
        // Returning empty list here because remote API is still not ready yet.
        return Observable.just(Collections.emptyList());
    }
}
