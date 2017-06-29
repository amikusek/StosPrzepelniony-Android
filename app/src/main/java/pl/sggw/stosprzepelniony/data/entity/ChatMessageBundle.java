package pl.sggw.stosprzepelniony.data.entity;

public class ChatMessageBundle {

    private int adId;
    private int userIdSender;
    private String message;

    public ChatMessageBundle(int adId, int userIdSender, String message) {
        this.adId = adId;
        this.userIdSender = userIdSender;
        this.message = message;
    }

    public int getAdId() {
        return adId;
    }

    public int getUserIdSender() {
        return userIdSender;
    }

    public String getMessage() {
        return message;
    }
}
