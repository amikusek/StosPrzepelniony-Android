package pl.sggw.stosprzepelniony.viper.ad_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.mateuszkoslacz.moviper.base.view.fragment.autoinject.passive.butterknife.ViperButterKnifePassiveFragment;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;

public class AdListFragment
        extends ViperButterKnifePassiveFragment<AdListContract.View>
        implements AdListContract.View {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
