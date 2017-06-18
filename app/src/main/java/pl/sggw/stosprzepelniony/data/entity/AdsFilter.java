package pl.sggw.stosprzepelniony.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class AdsFilter {

    private int userId;
    private List<Integer> categoryId;
    private String subject;
    private String content;
    private @SerializedName("costTotal") Cost totalCost;
    private @SerializedName("costHour") Cost hourCost;

    public AdsFilter(int userId, List<Integer> categoryId, String subject, String content, Cost totalCost, Cost hourCost) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.subject = subject;
        this.content = content;
        this.totalCost = totalCost;
        this.hourCost = hourCost;
    }

    public static AdsFilter provideDefault() {
        return new AdsFilter(0, Collections.emptyList(), "", "", new Cost(), new Cost());
    }
}
