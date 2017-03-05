package net.tlalka.android.fiszki.test.modules;

import android.content.Context;
import net.tlalka.android.fiszki.core.modules.SessionModule;
import net.tlalka.android.fiszki.model.db.DbHelper;
import net.tlalka.android.fiszki.model.helpers.AssetsHelper;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;

public class TestSessionModule extends SessionModule {

    public TestSessionModule(Context context) {
        super(context);
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public DbHelper getDbHelper() {
        return super.getDbHelper();
    }

    @Override
    public StorageHelper getStorageHelper() {
        return super.getStorageHelper();
    }

    @Override
    public AssetsHelper getAssetsHelper() {
        return super.getAssetsHelper();
    }
}
