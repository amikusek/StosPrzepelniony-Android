package pl.sggw.stosprzepelniony.viper.categories.adapter.view_holder;

import android.graphics.Typeface;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import pl.sggw.stosprzepelniony.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class RootViewHolder extends GroupViewHolder {

    private TextView rootCategoryTitle;
    private ImageView arrow;
    private int primaryColor;
    private int textColor;

    public RootViewHolder(View itemView) {
        super(itemView);
        rootCategoryTitle = (TextView) itemView.findViewById(R.id.listTitle);
        arrow = (ImageView) itemView.findViewById(R.id.indicator);
        primaryColor = itemView.getResources().getColor(R.color.colorPrimaryDark);
        textColor = itemView.getResources().getColor(R.color.default_text);
    }

    public void setRootCategoryTitle(ExpandableGroup group) {
        rootCategoryTitle.setText(group.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
        rootCategoryTitle.setTypeface(Typeface.DEFAULT_BOLD);
        rootCategoryTitle.setTextColor(primaryColor);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
        rootCategoryTitle.setTypeface(null, Typeface.NORMAL);
        rootCategoryTitle.setTextColor(textColor);
    }
}
