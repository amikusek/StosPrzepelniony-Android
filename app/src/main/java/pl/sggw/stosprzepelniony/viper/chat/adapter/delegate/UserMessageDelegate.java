package pl.sggw.stosprzepelniony.viper.chat.adapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageItem;
import pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageListItem;
import pl.sggw.stosprzepelniony.viper.chat.adapter.view_holder.UserMessageViewHolder;

import static pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageItem.USER_MESSAGE_TYPE;

public class UserMessageDelegate extends AdapterDelegate<List<MessageListItem>> {

    @Override
    protected boolean isForViewType(@NonNull List<MessageListItem> items, int position) {
        return items.get(position).getType() == USER_MESSAGE_TYPE;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new UserMessageViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.message_user, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<MessageListItem> items, int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        ((UserMessageViewHolder) holder).bindDataObject(((MessageItem) items.get(position)).getMessage());
    }
}
