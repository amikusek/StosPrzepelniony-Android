package pl.sggw.stosprzepelniony.viper.ad_list;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.mateuszkoslacz.moviper.base.view.fragment.autoinject.passive.butterknife.ViperButterKnifePassiveFragment;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.Ad;
import pl.sggw.stosprzepelniony.data.entity.AdsFilter;
import pl.sggw.stosprzepelniony.viper.ad_list.adapter.AdListAdapter;
import pl.sggw.stosprzepelniony.viper.ad_list.item.AdItem;
import pl.sggw.stosprzepelniony.viper.ad_list.item.AdListItem;

public class AdListFragment
        extends ViperButterKnifePassiveFragment<AdListContract.View>
        implements AdListContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.errorContainer)
    View errorContainer;
    @BindView(R.id.viewLoading)
    View viewLoading;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private AdsFilter mAdsFilter;
    private AdListAdapter mAdListAdapter = new AdListAdapter();
    private PublishSubject<Object> loadAdsSubject = PublishSubject.create();
    private PublishSubject<Object> adsFilterChangedSubject = PublishSubject.create();
    private PublishSubject<Object> refreshesSubject = PublishSubject.create();

    @Override
    protected void injectViews(View view) {
        super.injectViews(view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getContext()));
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdListAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
                linearLayoutManager.getOrientation()));
    }

    @Override
    public AdsFilter getAdsFilter() {
        return mAdsFilter != null ? mAdsFilter : AdsFilter.provideDefault();
    }

    @Override
    public Observable<Object> adsFilterChangedEvents() {
        return adsFilterChangedSubject;
    }

    @Override
    public Observable<Object> loadAdsEvents() {
        return loadAdsSubject;
    }

    @Override
    public Observable<Object> getFabEvents() {
        return RxView.clicks(fab);
    }

    @Override
    public Observable<Object> refreshes() {
        return RxSwipeRefreshLayout.refreshes(mSwipeRefreshLayout);
    }

    @Override
    public void setAdsItems(List<Ad> ads) {
        List<AdListItem> adItems = new ArrayList<>();
        for (Ad ad : ads) adItems.add(new AdItem(ad));
        mAdListAdapter.addItems(adItems);
        mRecyclerView.setVisibility(View.VISIBLE);
        viewLoading.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        mRecyclerView.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(Throwable throwable) {
        Toasty.error(getContext(), throwable.getMessage()).show();
        mSwipeRefreshLayout.setRefreshing(false);
        viewLoading.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public ViperPresenter<AdListContract.View> createPresenter() {
        return new AdListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ad_list;
    }
}
