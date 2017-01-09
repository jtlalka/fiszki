package net.tlalka.android.fiszki.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;
import net.tlalka.android.fiszki.core.AppFiszki;
import net.tlalka.android.fiszki.core.components.ActivityComponent;
import net.tlalka.android.fiszki.core.components.ApplicationComponent;
import net.tlalka.android.fiszki.core.components.SessionComponent;
import net.tlalka.android.fiszki.core.modules.ActivityModule;

import java.util.Locale;

public abstract class AbstractActivity extends Activity {

    protected ApplicationComponent getApplicationComponent() {
        return ((AppFiszki) getApplication()).getApplicationComponent();
    }

    protected SessionComponent getSessionComponent() {
        return ((AppFiszki) getApplication()).getSessionComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return this.getSessionComponent().add(new ActivityModule(this));
    }

    public void startActivity(Class<?> classValue) {
        super.startActivity(new Intent(super.getApplicationContext(), classValue));
    }

    public boolean createMenu(int menuResource, Menu menu) {
        super.getMenuInflater().inflate(menuResource, menu);
        return true;
    }

    public String localFormat(String format, Object... objects) {
        return String.format(Locale.getDefault(), format, objects);
    }

    public void alert(String message) {
        this.alert(message, Toast.LENGTH_SHORT);
    }

    public void alert(String message, int timeToast) {
        Toast.makeText(super.getBaseContext(), message, timeToast).show();
    }
}
