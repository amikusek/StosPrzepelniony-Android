package pl.sggw.stosprzepelniony.data.entity;

public class MessageBundle {

    private int adId;
    private int senderUserId;
    private String senderName;

    public MessageBundle(int adId, int senderUserId, String senderName) {
        this.adId = adId;
        this.senderUserId = senderUserId;
        this.senderName = senderName;
    }

    public int getAdId() {
        return adId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public String getSenderName() {
        return senderName;
    }

}
