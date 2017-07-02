package pl.sggw.stosprzepelniony.data.network.util;

import pl.sggw.stosprzepelniony.data.entity.ChatMessageBundle;
import pl.sggw.stosprzepelniony.data.entity.Message;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StosPrzepelnionyMessagesAPI {

    @GET("messages/{adId}/{userIdSender}")
    Observable<List<Message>> getMessagesForUser(
            @Path("adId") int adId,
            @Path("userIdSender") int userIdSender);

    @POST("messages/list")
    Observable<List<MessageListItem>> getConversations();

    @POST("messages/{adId}/{userIdSender}/send")
    Completable sendMessage(
            @Path("adId") int adId,
            @Path("userIdSender") int userIdSender,
            @Body ChatMessageBundle chatMessageBundle);
}
