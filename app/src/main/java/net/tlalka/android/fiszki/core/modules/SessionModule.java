package net.tlalka.android.fiszki.core.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.model.db.DbHelper;
import net.tlalka.android.fiszki.model.db.DbManager;
import net.tlalka.android.fiszki.model.helpers.AssetsHelper;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;

@Module
public class SessionModule {

    private final Context context;

    public SessionModule(Context context) {
        this.context = context;
    }

    @Provides
    @SessionScope
    public Context getContext() {
        return this.context;
    }

    @Provides
    @SessionScope
    public DbHelper getDbHelper() {
        return DbManager.getHelper(this.context);
    }

    @Provides
    @SessionScope
    public StorageHelper getStorageHelper() {
        return new StorageHelper(this.context);
    }

    @Provides
    @SessionScope
    public AssetsHelper getAssetsHelper() {
        return new AssetsHelper(this.context);
    }
}
