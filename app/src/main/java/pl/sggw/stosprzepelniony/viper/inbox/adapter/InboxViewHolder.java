package pl.sggw.stosprzepelniony.viper.inbox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.sggw.stosprzepelniony.R;

public class InboxViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.message_author)
    TextView author;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.user_initials)
    TextView userInitials;

    public InboxViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
