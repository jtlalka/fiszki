package net.tlalka.android.fiszki.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import net.tlalka.android.fiszki.models.db.DbHelper;
import net.tlalka.android.fiszki.models.db.DbManager;
import net.tlalka.android.fiszki.services.AssetsHelper;
import net.tlalka.android.fiszki.services.StorageHelper;
import net.tlalka.android.fiszki.utils.ValidUtils;

import java.util.Locale;

public abstract class AbstractActivity extends Activity {

    private DbHelper dbHelper;
    private AssetsHelper assetsHelper;
    private StorageHelper storageHelper;

    protected DbHelper getDbHelper() {
        if (ValidUtils.isNull(this.dbHelper)) {
            this.dbHelper = DbManager.getHelper(this);
        }
        return this.dbHelper;
    }

    protected AssetsHelper getAssetsHelper() {
        if (ValidUtils.isNull(this.assetsHelper)) {
            this.assetsHelper = new AssetsHelper(this);
        }
        return this.assetsHelper;
    }

    protected StorageHelper getStorageHelper() {
        if (ValidUtils.isNull(this.storageHelper)) {
            this.storageHelper = new StorageHelper(this);
        }
        return this.storageHelper;
    }

    public void startActivity(Class<?> classValue) {
        super.startActivity(new Intent(super.getApplicationContext(), classValue));
    }

    public void startActivity(Class<?> classValue, Bundle bundleToSend) {
        Intent intent = new Intent(super.getApplicationContext(), classValue);
        intent.putExtras(bundleToSend);
        super.startActivity(intent);
    }

    public boolean createMenu(int menuResource, Menu menu) {
        super.getMenuInflater().inflate(menuResource, menu);
        return true;
    }

    public String localFormat(String format, Object... objects) {
        return String.format(Locale.ENGLISH, format, objects);
    }

    public void alert(String message) {
        this.alert(message, Toast.LENGTH_SHORT);
    }

    public void alert(String message, int timeToast) {
        Toast.makeText(super.getBaseContext(), message, timeToast).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ValidUtils.isNotNull(dbHelper)) {
            DbManager.releaseHelper();
        }
        if (ValidUtils.isNotNull(this.assetsHelper)) {
            this.assetsHelper = null;
        }
        if (ValidUtils.isNotNull(this.storageHelper)) {
            this.storageHelper = null;
        }
    }
}
