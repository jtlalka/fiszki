package net.tlalka.android.fiszki.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import net.tlalka.android.fiszki.injections.components.ActivityComponent;
import net.tlalka.android.fiszki.injections.components.ApplicationComponent;
import net.tlalka.android.fiszki.injections.components.SessionComponent;
import net.tlalka.android.fiszki.injections.modules.ActivityModule;
import net.tlalka.android.fiszki.models.db.DbHelper;
import net.tlalka.android.fiszki.models.db.DbManager;
import net.tlalka.android.fiszki.services.AppHelper;
import net.tlalka.android.fiszki.services.StorageHelper;

import java.util.Locale;

public abstract class AbstractActivity extends Activity {

    protected ApplicationComponent getApplicationComponent() {
        return ((AppHelper) getApplication()).getApplicationComponent();
    }

    protected SessionComponent getSessionComponent() {
        return ((AppHelper) getApplication()).getSessionComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return this.getSessionComponent().add(new ActivityModule(this));
    }

    //TODO: use injections
    protected DbHelper getDbHelper() {
        return DbManager.getHelper(this);
    }

    //TODO: use injections
    protected StorageHelper getStorageHelper() {
        return new StorageHelper(this);
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
}
