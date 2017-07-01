package pl.sggw.stosprzepelniony.data.entity;

import static pl.sggw.stosprzepelniony.R.string.salary;

public class NewAdvertisementBundle {

    private int categoryId;
    private String subject;
    private String content;
    private float costHour;
    private float costTotal;

    public NewAdvertisementBundle(String subject, String content, int categoryId, float costHour, float costTotal) {
        this.subject = subject;
        this.content = content;
        this.categoryId = categoryId;
        this.costHour = costHour;
        this.costTotal = costTotal;
    }

    public String getSubject() {
        return subject;
    }

    public double getSalary() {
        return salary;
    }

    public String getContent() {
        return content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getCostHour() {
        return costHour;
    }

    public double getCostTotal() {
        return costTotal;
    }
}
