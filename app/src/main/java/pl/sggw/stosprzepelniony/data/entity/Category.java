package pl.sggw.stosprzepelniony.data.entity;

public class Category
{
    private String categoryIdParent;
    private String description;
    private String name;
    private int categoryId;

    public Category(String name) {
        this.name = name;
    }

    public String getCategoryIdParent ()
    {
        return categoryIdParent;
    }

    public String getDescription ()
    {
        return description;
    }

    public String getName ()
    {
        return name;
    }

    public int getCategoryId ()
    {
        return categoryId;
    }

}
