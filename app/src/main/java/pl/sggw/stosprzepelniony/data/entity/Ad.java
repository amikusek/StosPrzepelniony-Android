package pl.sggw.stosprzepelniony.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Ad {

    @SerializedName("adId")
    private int id;
    private User user;
    private Category category;
    @SerializedName("subject")
    private String title;
    private String content;
    @SerializedName("costTotal")
    private float totalCost;
    @SerializedName("costHour")
    private float hourCost;
    private Date date;

    public Ad(int id, User user, String title, String content, float totalCost, float hourCost, Date date) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.totalCost = totalCost;
        this.hourCost = hourCost;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public float getHourCost() {
        return hourCost;
    }

    public Date getDate() {
        return date;
    }
}

