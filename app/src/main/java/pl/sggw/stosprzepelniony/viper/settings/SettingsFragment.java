package pl.sggw.stosprzepelniony.viper.settings;


import android.os.Bundle;

import pl.sggw.stosprzepelniony.R;

public class SettingsFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_screen);
    }
}
