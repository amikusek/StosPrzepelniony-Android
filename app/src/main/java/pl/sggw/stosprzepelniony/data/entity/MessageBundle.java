package pl.sggw.stosprzepelniony.data.entity;

public class MessageBundle {

    private int adId;
    private int senderUserId;

    public MessageBundle(int adId, int senderUserId) {
        this.adId = adId;
        this.senderUserId = senderUserId;
    }

    public int getAdId() {
        return adId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

}
