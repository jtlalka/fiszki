package net.tlalka.android.fiszki.model.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DbManager {

    public static DbHelper getHelper(Context context) {
        return OpenHelperManager.getHelper(context, DbHelper.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
    }
}
