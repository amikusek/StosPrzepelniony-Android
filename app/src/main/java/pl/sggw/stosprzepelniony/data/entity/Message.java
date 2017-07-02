package pl.sggw.stosprzepelniony.data.entity;

public class Message {

    private int messageId;
    private int userIdSender;
    private String content;
    private String date;
    private User user;

    public Message(int messageId, int userIdSender, String content, String date, User user) {
        this.messageId = messageId;
        this.userIdSender = userIdSender;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getUserIdSender() {
        return userIdSender;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }
}
