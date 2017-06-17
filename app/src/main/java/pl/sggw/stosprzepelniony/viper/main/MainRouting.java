package pl.sggw.stosprzepelniony.viper.main;

import android.app.Activity;
import android.widget.Toast;

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting;

import pl.sggw.stosprzepelniony.data.event.NavigationDrawerItemSelectedEvent;
import pl.sggw.stosprzepelniony.util.constant.NavigationItem;

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
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start InboxActivity here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startUserProfileActivity() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start UserProfile here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startSettingsActivity() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start SettingsActivity here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void startLoginActivity() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: start LoginActivity here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void replaceByAdvertisementsFragment() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: replace fragment by AdvertisementsFragment here", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void replaceByCategoriesFragment() {
        if (isContextAttached()) {
            Toast.makeText(getRelatedContext(), "Routing: replace fragment by CategoriesFragment here", Toast.LENGTH_SHORT).show();
        }
    }
}
