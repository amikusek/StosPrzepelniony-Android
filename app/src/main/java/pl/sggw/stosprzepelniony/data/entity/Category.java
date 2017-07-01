package pl.sggw.stosprzepelniony.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable{

    int categoryId;
    String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    protected Category(Parcel in) {
        categoryName = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(categoryName);
    }
}
