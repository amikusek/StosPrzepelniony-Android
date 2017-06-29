package pl.sggw.stosprzepelniony;

import android.app.Application;

import pl.sggw.stosprzepelniony.di.DIProvider;

public class StosPrzepelnionyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DIProvider.getInstance().init(this);
    }
}
