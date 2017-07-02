package pl.sggw.stosprzepelniony.viper.chat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.ChatMessageBundle;
import pl.sggw.stosprzepelniony.data.entity.Message;
import pl.sggw.stosprzepelniony.exception.BaseException;
import pl.sggw.stosprzepelniony.util.constant.Irrelevant;
import pl.sggw.stosprzepelniony.viper.chat.adapter.MessagesAdapter;
import pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageItem;
import pl.sggw.stosprzepelniony.viper.chat.adapter.item.MessageListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ChatActivity
        extends ViperButterKnifePassiveActivity
        <ChatContract.View>
        implements ChatContract.View {

    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    LinearLayout errorView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btn_send)
    ImageButton sendButton;
    @BindView(R.id.message_field)
    EditText messageField;

    private int interlocutorId;
    private int adId;
    private String interlocutorName;

    public static void start(Context context, int interlocutorId, int adId, String name) {
        Intent starter = new Intent(context, ChatActivity.class);
        starter.putExtra(USER_ID_BUNDLE, interlocutorId);
        starter.putExtra(AD_ID_BUNDLE, adId);
        starter.putExtra(INTERLOCUTOR_NAME_BUNDLE, name);
        context.startActivity(starter);
    }

    private MessagesAdapter messagesAdapter = new MessagesAdapter();
    private PublishSubject<Object> backButtonClicks = PublishSubject.create();

    @Override
    public Observable<Object> getBackButtonClicks() {
        return backButtonClicks;
    }

    @Override
    public Observable<ChatMessageBundle> getSendButtonClicks() {
        return RxView.
                clicks(sendButton)
                .map(s -> new ChatMessageBundle(adId, interlocutorId, messageField.getText().toString()));
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(Throwable throwable, boolean showErrorView) {
        if (showErrorView) showErrorView();

        if (throwable instanceof BaseException) {
            Toasty.error(this, ((BaseException) throwable).getUserMessage(this), Toast.LENGTH_LONG, true).show();
        } else {
            Toasty.error(this, getString(R.string.error_default), Toast.LENGTH_LONG, true).show();
        }
    }

    private void showErrorView() {
        loadingView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateView() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        messageField.setText("");
    }

    @Override
    public void showContent(List<Message> messages) {
        List<MessageListItem> messageItems = new ArrayList<>();
        for (Message message : messages) messageItems.add(new MessageItem(message));
        messagesAdapter.setMessageList(messageItems);
        loadingView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void showEmptyState() {
        emptyView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void injectViews() {
        super.injectViews();
        getExtras();
        setUpToolbar();
        initRecyclerView();
    }

    private void getExtras() {
        interlocutorId = getIntent().getIntExtra(USER_ID_BUNDLE, 1);
        adId = getIntent().getIntExtra(AD_ID_BUNDLE, 1);
        interlocutorName = getIntent().getStringExtra(INTERLOCUTOR_NAME_BUNDLE);
    }

    private void setUpToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(interlocutorName);
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(messagesAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            backButtonClicks.onNext(Irrelevant.EVENT);
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public ViperPresenter<ChatContract.View> createPresenter() {
        return new ChatPresenter(getArgs());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }
}
