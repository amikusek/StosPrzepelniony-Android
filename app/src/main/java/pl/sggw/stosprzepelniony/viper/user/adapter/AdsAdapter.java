package pl.sggw.stosprzepelniony.viper.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Ad;

public class AdsAdapter extends RecyclerView.Adapter<AdViewHolder> {

    private List<Ad> userAds = new ArrayList<>();
    public PublishSubject<Ad> userAdsClicks = PublishSubject.create();

    public void setList(List<Ad> userAds) {
        this.userAds = userAds;
        notifyDataSetChanged();
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.user_ad_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        holder.adSubject.setText(userAds.get(position).getTitle());
        RxView.clicks(holder.itemView).map(event -> userAds.get(position)).subscribe(userAdsClicks);
    }

    @Override
    public int getItemCount() {
        return userAds.size();
    }
}
