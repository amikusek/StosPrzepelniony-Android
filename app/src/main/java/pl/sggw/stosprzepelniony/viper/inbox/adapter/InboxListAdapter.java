package pl.sggw.stosprzepelniony.viper.inbox.adapter;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.MessageBundle;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;

public class InboxListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageListItem> items = new ArrayList<>();
    public PublishSubject<MessageBundle> messagesClicks = PublishSubject.create();
    private int[] colors;

    public void setList(List<MessageListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public InboxListAdapter(int[] colors) {
        this.colors = colors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InboxViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.inbox_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageBundle dummyBundle = new MessageBundle(1, 1);
        //TODO: This method is incomplete (remote API is still not ready yet).
        holder.itemView.findViewById(R.id.user_initials).getBackground().setColorFilter(colors[position % colors.length], PorterDuff.Mode.SRC);
        RxView.clicks(holder.itemView).map(event -> dummyBundle).subscribe(messagesClicks);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
