package pl.sggw.stosprzepelniony.viper.inbox.adapter;

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.MessageBundle;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;
import pl.sggw.stosprzepelniony.util.date.DateConverter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class InboxListAdapter extends RecyclerView.Adapter<InboxViewHolder> {

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
    public InboxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InboxViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.inbox_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(InboxViewHolder holder, int position) {

        MessageBundle dummyBundle = new MessageBundle(1, 1, "");
        holder.userInitials.getBackground().setColorFilter(colors[position % colors.length], PorterDuff.Mode.SRC);
        holder.date.setText(DateConverter.getFormattedDate(items.get(position).getDate()));
        RxView.clicks(holder.itemView).map(event -> dummyBundle).subscribe(messagesClicks);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
