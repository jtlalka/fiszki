package net.tlalka.android.fiszki.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;
import net.tlalka.android.fiszki.core.AppFiszki;
import net.tlalka.android.fiszki.core.components.ActivityComponent;

public abstract class AbstractActivity extends Activity {

    protected ActivityComponent getActivityComponent() {
        return ((AppFiszki) getApplication()).getActivityComponent(this);
    }

    public void startActivity(Class<?> classValue) {
        super.startActivity(new Intent(super.getApplicationContext(), classValue));
    }

    public boolean createMenu(int menuResource, Menu menu) {
        super.getMenuInflater().inflate(menuResource, menu);
        return true;
    }

    public void alert(String message) {
        this.alert(message, Toast.LENGTH_SHORT);
    }

    public void alert(String message, int timeToast) {
        Toast.makeText(super.getBaseContext(), message, timeToast).show();
    }
}
