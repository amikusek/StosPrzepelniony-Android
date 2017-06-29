package pl.sggw.stosprzepelniony.viper.chat.adapter.item;

import pl.sggw.stosprzepelniony.data.entity.Message;

public class MessageItem implements MessageListItem {

    public static int USER_MESSAGE_TYPE = 0;
    public static int SENDER_MESSAGE_TYPE = 1;

    // TODO: remove this static userId from here and assign a real one from proper place
    private int userId = 5;

    private Message message;

    public MessageItem(Message message) {
        this.message = message;
    }

    @Override
    public int getType() {
        return message.getUserIdSender() == userId ? USER_MESSAGE_TYPE : SENDER_MESSAGE_TYPE;
    }

    public Message getMessage() {
        return message;
    }
}
