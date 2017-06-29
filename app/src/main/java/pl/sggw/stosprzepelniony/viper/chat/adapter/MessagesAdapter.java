package pl.sggw.stosprzepelniony.viper.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

import pl.sggw.stosprzepelniony.viper.chat.adapter.delegate.InterlocutorMessageDelegate;
import pl.sggw.stosprzepelniony.viper.chat.adapter.delegate.UserMessageDelegate;
import pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageListItem;

import static pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageItem.SENDER_MESSAGE_TYPE;
import static pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageItem.USER_MESSAGE_TYPE;

public class MessagesAdapter extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<MessageListItem>> delegatesManager =
            new AdapterDelegatesManager<List<MessageListItem>>()
                    .addDelegate(USER_MESSAGE_TYPE, new UserMessageDelegate())
                    .addDelegate(SENDER_MESSAGE_TYPE, new InterlocutorMessageDelegate());
    private List<MessageListItem> messageList = new ArrayList<>();

    public void setMessageList(List<MessageListItem> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(messageList, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(messageList, position, holder);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
