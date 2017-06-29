package pl.sggw.stosprzepelniony.viper.chat.adapter.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Message;

import static butterknife.ButterKnife.bind;

public class UserMessageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.message)
    TextView messageContent;

    public UserMessageViewHolder(View itemView) {
        super(itemView);
        bind(this, itemView);
    }

    public void bindDataObject(Message message) {
        date.setText(String.valueOf(message.getDate()));
        messageContent.setText(message.getContent());
    }
}
