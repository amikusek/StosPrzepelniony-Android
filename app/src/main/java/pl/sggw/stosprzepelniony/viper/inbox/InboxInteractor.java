package pl.sggw.stosprzepelniony.viper.inbox;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;
import pl.sggw.stosprzepelniony.data.entity.User;
import pl.sggw.stosprzepelniony.data.network.util.RetrofitFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

class InboxInteractor
        extends BaseRxInteractor
        implements InboxContract.Interactor {

    private RetrofitFactory retrofitFactory = new RetrofitFactory();

    @Override
    public Observable<List<MessageListItem>> getMessages() {
        List<MessageListItem> messages = new ArrayList<>();
        User user1 = new User("Jacek", "Kowalski", "jacekkowalski@wp.pl");
        User user2 = new User("Wacław", "Potocki", "potocki23@wp.pl");
        User user3 = new User("Krystian", "Karczyński", "krystianhwdp@wp.pl");
        messages.add(new MessageListItem(5, 4, new Date(), new Ad(48, user3, "Ogłoszenie 3", "Opis trzeciego ogłoszenia", 19191, 0, new Date()), user3));
        messages.add(new MessageListItem(7, 10, new Date(System.currentTimeMillis()-900*6000), new Ad(10, user2, "Ogłoszenie 2", "Opis drugiego ogłoszenia", 1234, 0, new Date()), user2));
        messages.add(new MessageListItem(2, 15, new Date(System.currentTimeMillis()-900*9999), new Ad(5, user1, "Ogłoszenie 1", "Opis pierwszego ogłoszenia", 9999, 0, new Date()), user1));
        return Observable.just(messages);
/*        return retrofitFactory
                .getMessagesAPI()
                .getConversations()
                .subscribeOn(Schedulers.io());*/
    }
}
