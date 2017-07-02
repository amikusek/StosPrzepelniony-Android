package pl.sggw.stosprzepelniony.viper.ad_list.adapter.viewholder;

import com.google.android.flexbox.FlexboxLayout;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.util.date.DateConverter;
import pl.sggw.stosprzepelniony.util.view.ChipsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdViewHolder
        extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.categoriesContainer)
    FlexboxLayout categoriesContainer;

    public AdViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindDataObject(Ad ad) {
        title.setText(ad.getTitle());
        date.setText(DateConverter.getFormattedDate(ad.getDate()));
        cost.setText(createProperCostInformationForAd(ad));
        author.setText(String.format("by %s", ad.getUser().getFirstName()));
        if (categoriesContainer.getChildCount() == 0) {
            categoriesContainer.addView(new ChipsView(itemView.getContext()).withText(ad.getCategory().getCategoryName()));
        }
    }

    @SuppressLint("DefaultLocale")
    private String createProperCostInformationForAd(Ad ad) {
        if (ad.getTotalCost() != 0 && ad.getTotalCost() != 1) {
            return String.format("%d$ - total cost", ((int) ad.getTotalCost()));
        } else if (ad.getHourCost() != 0 && ad.getHourCost() != 1) {
            return String.format("%d$/per hour", ((int) ad.getHourCost()));
        } else {
            return "Missing cost information";
        }
    }
}

