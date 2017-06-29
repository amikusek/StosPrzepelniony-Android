package pl.sggw.stosprzepelniony.viper.chat;

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.data.entity.ChatMessageBundle;
import pl.sggw.stosprzepelniony.data.entity.User;
import pl.sggw.stosprzepelniony.data.entity.Message;

class ChatInteractor
        extends BaseRxInteractor
        implements ChatContract.Interactor {

    @Override
    public Observable<List<Message>> getMessagesFromUserById(int senderId) {

        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1, 5, "Hello! Can I ask you about the project?", new Date(), new User("Anna", "Nowak", "anowak@gmail.com")));
        messages.add(new Message(2, 1, "Hello. Sure, go ahead.", new Date(), new User("John","Doe","mail@mail.com")));
        messages.add(new Message(3, 5, "Are you working in SCRUM?", new Date(), new User("John","Doe","mail@com.com")));
        return Observable.just(messages);
    }

    @Override
    public Completable sendMessage(ChatMessageBundle bundle) {
        return Completable.complete();
    }
}
