package net.tlalka.android.fiszki.view.activities;

import android.view.Menu;
import android.view.MenuItem;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.view.elements.SetupElement;

public abstract class BasePageActivity extends AbstractActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return this.createMenu(R.menu.page_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return SetupElement.triggerAction(this, item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
