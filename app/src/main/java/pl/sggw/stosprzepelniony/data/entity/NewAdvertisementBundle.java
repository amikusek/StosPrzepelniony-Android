package pl.sggw.stosprzepelniony.data.entity;

public class NewAdvertisementBundle {

    private String subject;
    private double salary;
    private int salaryType;
    private String description;
    private int categoryId;

    public NewAdvertisementBundle(String subject, double salary, int salaryType, String description, int categoryId) {
        this.subject = subject;
        this.salary = salary;
        this.salaryType = salaryType;
        this.description = description;
        this.categoryId = categoryId;
    }

    public NewAdvertisementBundle(){}

    public String getSubject() {
        return subject;
    }

    public double getSalary() {
        return salary;
    }

    public int getSalaryType() {
        return salaryType;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
