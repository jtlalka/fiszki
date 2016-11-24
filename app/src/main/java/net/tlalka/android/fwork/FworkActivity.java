package net.tlalka.android.fwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public abstract class FworkActivity extends Activity {

    public void onCreate(Bundle bundle, int idLayout) {
        super.onCreate(bundle);
        super.setContentView(idLayout);
    }

    public boolean onCreateOptionsMenu(Menu menu, int idMenu) {
        super.getMenuInflater().inflate(idMenu, menu);
        return true;
    }

    public void startActivity(Class<?> classValue, Bundle bundleToSend) {
        bundleToSend.putString("bundleFromClass", super.getLocalClassName());

        Intent intent = new Intent(super.getApplicationContext(), classValue);
        intent.putExtras(bundleToSend);

        super.startActivity(intent);
    }

    public void alert(String message) {
        Toast.makeText(super.getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void alert(String message, int timeToast) {
        Toast.makeText(super.getBaseContext(), message, timeToast).show();
    }
}
