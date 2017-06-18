package pl.sggw.stosprzepelniony.viper.ad_list.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.viper.ad_list.adapter.viewholder.AdViewHolder;
import pl.sggw.stosprzepelniony.viper.ad_list.item.AdItem;
import pl.sggw.stosprzepelniony.viper.ad_list.item.AdListItem;

import java.util.List;

public class AdAdapterDelegate extends AdapterDelegate<List<AdListItem>> {

    @Override
    protected boolean isForViewType(@NonNull List<AdListItem> items, int position) {
        return items.get(position).getType() == AdItem.TYPE;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new AdViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.viewholder_ad, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<AdListItem> items, int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        ((AdViewHolder) holder).bindDataObject(((AdItem) items.get(position)).getAd());
    }
}
