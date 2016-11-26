package net.tlalka.android.fiszki.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import java.util.Locale;

public abstract class AbstractActivity extends Activity {

    public boolean onCreateOptionsMenu(Menu menu, int idMenu) {
        super.getMenuInflater().inflate(idMenu, menu);
        return true;
    }

    public void startActivity(Class<?> classValue, Bundle bundleToSend) {
        Intent intent = new Intent(super.getApplicationContext(), classValue);
        intent.putExtras(bundleToSend);
        super.startActivity(intent);
    }

    public String localFormat(String format, Object... objects) {
        return String.format(Locale.ENGLISH, format, objects);
    }

    public void alert(String message) {
        Toast.makeText(super.getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void alert(String message, int timeToast) {
        Toast.makeText(super.getBaseContext(), message, timeToast).show();
    }
}
