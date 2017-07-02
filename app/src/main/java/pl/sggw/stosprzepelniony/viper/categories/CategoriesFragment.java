package pl.sggw.stosprzepelniony.viper.categories;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.view.fragment.autoinject.passive.butterknife.ViperButterKnifePassiveFragment;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.exception.BaseException;
import pl.sggw.stosprzepelniony.viper.categories.adapter.CategoriesAdapter;
import pl.sggw.stosprzepelniony.viper.categories.adapter.item.RootCategory;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class CategoriesFragment
        extends ViperButterKnifePassiveFragment
        <CategoriesContract.View>
        implements CategoriesContract.View {

    private CategoriesAdapter categoriesAdapter;
    private PublishSubject<Integer> childrenClicks = PublishSubject.create();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.error_view)
    LinearLayout errorView;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Override
    protected void injectViews(View view) {
        super.injectViews(view);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(Throwable throwable) {
        if (throwable instanceof BaseException) {
            Toasty.error(getContext(), ((BaseException) throwable).getUserMessage(getContext()), Toast.LENGTH_LONG, true).show();
        } else {
            Toasty.error(getContext(), getString(R.string.error_default), Toast.LENGTH_LONG, true).show();
        }
    }

    @Override
    public void setMessagesList(List<RootCategory> items) {
        categoriesAdapter = new CategoriesAdapter(items);
        categoriesAdapter.childrenClicks.subscribe(childrenClicks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(categoriesAdapter);
        loadingView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public Observable<Integer> getListItemClicks() {
        return childrenClicks;
    }

    @NonNull
    @Override
    public ViperPresenter<CategoriesContract.View> createPresenter() {
        return new CategoriesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_categories;
    }
}
