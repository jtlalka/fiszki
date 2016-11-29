package net.tlalka.android.fiszki.activities;

import android.view.Menu;
import android.view.MenuItem;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.SetupElement;

public abstract class BaseSetupActivity extends AbstractActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return this.createMenu(R.menu.setup_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return SetupElement.triggerAction(this, item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
