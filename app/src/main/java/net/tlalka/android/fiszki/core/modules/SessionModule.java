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
