package pl.sggw.stosprzepelniony.data.util;

import pl.sggw.stosprzepelniony.data.entity.Category;

import java.util.List;

public class CategoriesCacheStorage {

    private List<Category> categories;
    private static volatile CategoriesCacheStorage mInstance;

    private CategoriesCacheStorage() {
    }

    public static CategoriesCacheStorage getInstance() {
        if (mInstance == null) {
            synchronized (CategoriesCacheStorage.class) {
                if (mInstance == null) {
                    mInstance = new CategoriesCacheStorage();
                }
            }
        }
        return mInstance;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getCategoryById(int id) {
        for (Category category : categories) if (category.getCategoryId() == id) return category;
        return null;
    }

}
