package net.tlalka.android.fiszki.injections.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import net.tlalka.android.fiszki.injections.annotations.SessionScope;
import net.tlalka.android.fiszki.models.db.DbHelper;
import net.tlalka.android.fiszki.models.db.DbManager;
import net.tlalka.android.fiszki.services.AssetsHelper;
import net.tlalka.android.fiszki.services.StorageHelper;

@Module
public class SessionModule {

    private final Context context;

    public SessionModule(Context context) {
        this.context = context;
    }

    @Provides
    @SessionScope
    Context context() {
        return this.context;
    }

    @Provides
    @SessionScope
    DbHelper dbHelper() {
        return DbManager.getHelper(this.context);
    }

    @Provides
    @SessionScope
    StorageHelper storageHelper() {
        return new StorageHelper(this.context);
    }

    @Provides
    @SessionScope
    AssetsHelper assetsHelper() {
        return new AssetsHelper(this.context);
    }
}
