package pl.sggw.stosprzepelniony.viper.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.sggw.stosprzepelniony.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (getSupportActionBar() != null )
            getSupportActionBar().setTitle(R.string.settings);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();

    }
}
