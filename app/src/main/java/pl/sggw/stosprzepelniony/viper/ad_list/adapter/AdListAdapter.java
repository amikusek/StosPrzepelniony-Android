package pl.sggw.stosprzepelniony.viper.ad_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import com.jakewharton.rxbinding2.view.RxView;

import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.viper.ad_list.adapter.delegate.AdAdapterDelegate;
import pl.sggw.stosprzepelniony.viper.ad_list.item.AdItem;
import pl.sggw.stosprzepelniony.viper.ad_list.item.AdListItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class AdListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PublishSubject<Ad> adClicks = PublishSubject.create();
    private List<AdListItem> items = new ArrayList<>();
    private AdapterDelegatesManager<List<AdListItem>> delegatesManager =
            new AdapterDelegatesManager<List<AdListItem>>()
                    .addDelegate(AdItem.TYPE, new AdAdapterDelegate());


    public void addItems(List<AdListItem> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
        RxView.clicks(holder.itemView)
                .map(event -> items.get(position))
                .cast(AdItem.class)
                .map(AdItem::getAd)
                .subscribe(adClicks);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public PublishSubject<Ad> getAdClicks() {
        return adClicks;
    }
}




