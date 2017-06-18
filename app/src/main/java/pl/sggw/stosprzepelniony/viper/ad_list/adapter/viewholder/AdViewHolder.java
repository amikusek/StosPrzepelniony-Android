package pl.sggw.stosprzepelniony.viper.ad_list.adapter.viewholder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Ad;

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
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.categoriesContainer)
    LinearLayout categoriesContainer;

    public AdViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindDataObject(Ad ad) {
        title.setText(ad.getTitle());
        date.setText(String.valueOf(ad.getDate()));
        cost.setText(createProperCostInformationFromAd(ad));
        content.setText(ad.getContent());
    }

    @SuppressLint("DefaultLocale")
    private String createProperCostInformationFromAd(Ad ad) {
        if (ad.getTotalCost() != 0) {
            return String.format("%d$ - total cost", ((int) ad.getTotalCost()));
        } else if (ad.getHourCost() != 0) {
            return String.format("%d$/per hour", ((int) ad.getHourCost()));
        } else {
            return "Missing cost information";
        }
    }
}

