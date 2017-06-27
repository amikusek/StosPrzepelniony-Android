package pl.sggw.stosprzepelniony.viper.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.sggw.stosprzepelniony.R;

public class AdViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ad_icon)
    ImageView adIcon;
    @BindView(R.id.ad_subject)
    TextView adSubject;

    public AdViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
