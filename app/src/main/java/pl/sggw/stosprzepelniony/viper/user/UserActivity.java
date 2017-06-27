package pl.sggw.stosprzepelniony.viper.user;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.User;
import pl.sggw.stosprzepelniony.exception.BaseException;
import pl.sggw.stosprzepelniony.viper.user.adapter.AdsAdapter;

public class UserActivity
        extends ViperButterKnifePassiveActivity<UserContract.View>
        implements UserContract.View {

    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    LinearLayout errorView;
    @BindView(R.id.user_initials)
    TextView userInitials;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_email)
    TextView userEmail;

    private AdsAdapter adsAdapter = new AdsAdapter();

    public static final String USER_ID_BUNDLE = "USER_ID";

    public static void start(Context context, int userId) {
        Intent starter = new Intent(context, UserActivity.class);
        starter.putExtra(USER_ID_BUNDLE, userId);
        context.startActivity(starter);
    }

    @Override
    public Observable<Ad> getListItemClicks() {
        return adsAdapter.userAdsClicks;
    }

    @Override
    public void showEmptyState() {
        loadingView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent(List<Ad> userAds) {
        adsAdapter.setList(userAds);
        loadingView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(Throwable throwable) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        if (throwable instanceof BaseException) {
            Toasty.error(this, ((BaseException) throwable).getUserMessage(this), Toast.LENGTH_LONG, true).show();
        } else {
            Toasty.error(this, getString(R.string.error_default), Toast.LENGTH_LONG, true).show();
        }
    }

    @Override
    public void showUserInfo(User user) {
        userInitials.setText(""+user.getFirstName().charAt(0) + user.getLastName().charAt(0));
        userName.setText(user.getFirstName() +" "+ user.getLastName());
        userEmail.setText(user.getEmail());
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation()));
    }


    @Override
    protected void injectViews() {
        super.injectViews();
        setToolbar();
        initRecyclerView();
    }

    @NonNull
    @Override
    public ViperPresenter<UserContract.View> createPresenter() {
        return new UserPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }
}
