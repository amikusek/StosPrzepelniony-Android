package pl.sggw.stosprzepelniony.viper.settings;


import android.os.Bundle;
import android.preference.SwitchPreference;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.di.DIProvider;

public class SettingsFragment extends android.preference.PreferenceFragment {

    public SwitchPreference autoLoginSwitch;
    private final String AUTO_LOGIN_KEY = "AutoSignIn";

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_screen);
        autoLoginSwitch = (SwitchPreference) findPreference(AUTO_LOGIN_KEY);
        autoLoginSwitch.setChecked(DIProvider.getInstance().getPersistentStorage().isAutoSignInEnabled());
        autoLoginSwitch.setOnPreferenceChangeListener((preference, newValue) -> {
            DIProvider.getInstance().getPersistentStorage().setAutoSignInEnabled((boolean) newValue);
            autoLoginSwitch.setChecked((boolean) newValue);
            return false;
        });
    }
}
