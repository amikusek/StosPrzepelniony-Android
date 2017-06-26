package pl.sggw.stosprzepelniony.viper.inbox;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.MessageBundle;
import pl.sggw.stosprzepelniony.data.entity.MessageListItem;
import pl.sggw.stosprzepelniony.exception.BaseException;
import pl.sggw.stosprzepelniony.viper.inbox.adapter.InboxListAdapter;

public class InboxActivity
        extends ViperButterKnifePassiveActivity
        <InboxContract.View>
        implements InboxContract.View {

    @BindView(R.id.empty_inbox)
    LinearLayout emptyInboxView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private InboxListAdapter inboxListAdapter;

    @Override
    public Observable<MessageBundle> getListItemClicks() {
        return inboxListAdapter.messagesClicks;
    }

    public void showLoading() {
        emptyInboxView.setVisibility(View.INVISIBLE);
        loadingView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    public void showError(Throwable throwable) {
        if (throwable instanceof BaseException) {
            Toasty.error(this, ((BaseException) throwable).getUserMessage(this), Toast.LENGTH_LONG, true).show();
        } else {
            Toasty.error(this, getString(R.string.error_default), Toast.LENGTH_LONG, true).show();
        }
    }

    public void setMessagesList(List<MessageListItem> items) {
        emptyInboxView.setVisibility(View.INVISIBLE);
        loadingView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        inboxListAdapter.setList(items);
    }

    public void showEmptyState() {
        emptyInboxView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void initRecyclerView() {
        inboxListAdapter = new InboxListAdapter(getResources().getIntArray(R.array.messages_colors));
        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(inboxListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation()));
    }

    private void setToolbar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(R.string.inbox);
    }

    @Override
    protected void injectViews() {
        super.injectViews();
        initRecyclerView();
        setToolbar();
    }

    @NonNull
    @Override
    public ViperPresenter<InboxContract.View> createPresenter() {
        return new InboxPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_inbox;
    }
}
