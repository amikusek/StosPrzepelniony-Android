package pl.sggw.stosprzepelniony.viper.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.ViperAiPassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.event.NavigationDrawerItemSelectedEvent;
import pl.sggw.stosprzepelniony.util.constant.NavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity
        extends ViperAiPassiveActivity
        <MainContract.View>
        implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    PublishSubject<NavigationDrawerItemSelectedEvent> navigationDrawerClicks = PublishSubject.create();

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.app_fullname));
    }

    private void setUpNavigationDrawer() {
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public Observable<NavigationDrawerItemSelectedEvent> getNavigationDrawerClicks() {
        return navigationDrawerClicks;
    }

    @Override
    protected void injectViews() {
        ButterKnife.bind(this);
        setUpToolbar();
        setUpNavigationDrawer();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        navigationDrawerClicks.onNext(new NavigationDrawerItemSelectedEvent(mapMenuItemToMenuPosition(item)));
        return true;
    }

    @Override
    public void closeDrawer() { drawer.closeDrawer(GravityCompat.START); }

    @Override
    public void showError(Throwable throwable) {
        Toasty.error(this, throwable.getMessage(), Toast.LENGTH_LONG, true).show();
    }

    @Override
    public void showToast(String text) {
        Toasty.info(this, text, Toast.LENGTH_LONG, true).show();
    }

    private int mapMenuItemToMenuPosition(MenuItem item) {
        if(item.getItemId() == R.id.nav_ads) return NavigationItem.ADS;
        else if(item.getItemId() == R.id.nav_categories) return NavigationItem.CATEGORIES;
        else if(item.getItemId() == R.id.nav_inbox) return NavigationItem.INBOX;
        else if(item.getItemId() == R.id.nav_profile) return NavigationItem.PROFILE;
        else if(item.getItemId() == R.id.nav_settings) return NavigationItem.SETTINGS;
        else if(item.getItemId() == R.id.nav_logout) return NavigationItem.LOGOUT;
        else return NavigationItem.ADS;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    public ViperPresenter<MainContract.View> createPresenter() {
        return new MainPresenter();
    }

}
