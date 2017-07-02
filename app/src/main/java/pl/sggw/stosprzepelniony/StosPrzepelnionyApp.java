package pl.sggw.stosprzepelniony;

import android.app.Application;

import com.facebook.stetho.Stetho;

import pl.sggw.stosprzepelniony.di.DIProvider;

public class StosPrzepelnionyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DIProvider.getInstance().init(this);
        Stetho.initializeWithDefaults(this);
    }
}
