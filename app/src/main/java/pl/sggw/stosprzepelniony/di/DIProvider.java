package pl.sggw.stosprzepelniony.di;

import android.content.Context;

public class DIProvider {

    private static DIProvider instance;
    private Context appContext;

    private DIProvider() {
    }

    public static DIProvider getInstance() {
        if (instance == null) {
            instance = new DIProvider();
        }
        return instance;
    }

    public void init(Context context) {
        this.appContext = context;
    }

    private void assertNonNullContext() {
        if (appContext == null) {
            throw new IllegalStateException("You have to init DIProvider with application context first");
        }
    }
}