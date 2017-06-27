package pl.sggw.stosprzepelniony.data.entity;

import java.util.Date;

public class MessageListItem {

    private int receiverId;
    private int senderId;
    private Date date;
    private Ad ad;
    private User user;

    public MessageListItem(int receiverId, int senderId, Date date, Ad ad, User user) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.date = date;
        this.ad = ad;
        this.user = user;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public Date getDate() {
        return date;
    }

    public Ad getAd() {
        return ad;
    }

    public User getUser() {
        return user;
    }
}
