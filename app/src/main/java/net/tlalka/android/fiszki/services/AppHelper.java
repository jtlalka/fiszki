package net.tlalka.android.fiszki.services;

import android.app.Application;
import android.content.Context;

public class AppHelper extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
