package pl.sggw.stosprzepelniony.viper.main;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.event.NavigationDrawerItemSelectedEvent;
import pl.sggw.stosprzepelniony.util.constant.NavigationItem;
import pl.sggw.stosprzepelniony.viper.ad_list.AdListFragment;
import pl.sggw.stosprzepelniony.viper.categories.CategoriesFragment;
import pl.sggw.stosprzepelniony.viper.inbox.InboxActivity;
import pl.sggw.stosprzepelniony.viper.login.LoginActivity;
import pl.sggw.stosprzepelniony.viper.settings.SettingsActivity;
import pl.sggw.stosprzepelniony.viper.user.UserActivity;

class MainRouting
        extends BaseRxRouting<Activity>
        implements MainContract.Routing {

    public void startProperScreenForNavigationEvent(NavigationDrawerItemSelectedEvent event) {
        if (isContextAttached()) {
            if (event.getNavigationItem() == NavigationItem.ADS)
                replaceByAdvertisementsFragment();
            else if (event.getNavigationItem() == NavigationItem.CATEGORIES)
                replaceByCategoriesFragment();
            else if (event.getNavigationItem() == NavigationItem.INBOX)
                startInboxActivity();
            else if (event.getNavigationItem() == NavigationItem.PROFILE)
                startUserProfileActivity();
            else if (event.getNavigationItem() == NavigationItem.SETTINGS)
                startSettingsActivity();
            else if (event.getNavigationItem() == NavigationItem.LOGOUT)
                startLoginActivity();
        }
    }

    @Override
    public void startInboxActivity() {
        if (isContextAttached()) InboxActivity.start(getRelatedContext());
    }

    @Override
    public void startUserProfileActivity() {
        if (isContextAttached()) UserActivity.start(getRelatedContext(), 5);
    }

    @Override
    public void startSettingsActivity() {
        if (isContextAttached()) SettingsActivity.start(getRelatedContext());
    }

    @Override
    public void startLoginActivity() {
        if (isContextAttached()) LoginActivity.start(getRelatedContext());
    }

    @Override
    public void replaceByAdvertisementsFragment() {
        if (isContextAttached()) {
            ((AppCompatActivity) getRelatedContext())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main_view, AdListFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void replaceByCategoriesFragment() {
        if (isContextAttached()) {
            ((AppCompatActivity) getRelatedContext())
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main_view, CategoriesFragment.newInstance())
                    .commit();
        }
    }
}
