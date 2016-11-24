package net.tlalka.android.fiszki.models;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DatabaseManager {

    public static DatabaseHelper getHelper(Context context) {
        return OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
    }
}
