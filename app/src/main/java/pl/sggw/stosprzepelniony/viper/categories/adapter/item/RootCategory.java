package pl.sggw.stosprzepelniony.viper.categories.adapter.item;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import pl.sggw.stosprzepelniony.data.entity.Category;

public class RootCategory extends ExpandableGroup<Category> {

    public RootCategory(String title, List<Category> children) {
        super(title, children);
    }
}
