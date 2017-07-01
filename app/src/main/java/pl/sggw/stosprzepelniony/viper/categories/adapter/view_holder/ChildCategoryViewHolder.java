package pl.sggw.stosprzepelniony.viper.categories.adapter.view_holder;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Category;

public class ChildCategoryViewHolder extends ChildViewHolder {

    private TextView childCategoryName;

    public ChildCategoryViewHolder(View itemView) {
        super(itemView);
        childCategoryName = (TextView) itemView.findViewById(R.id.childTitle);
    }

    public void onBind(Category category) {
        childCategoryName.setText(category.getCategoryName());
    }
}