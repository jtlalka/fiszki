package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.SetupElement;
import net.tlalka.android.fiszki.models.DbHelper;
import net.tlalka.android.fiszki.models.DbManager;

public abstract class BasePageActivity extends AbstractActivity {

    protected DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.dbHelper = DbManager.getHelper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DbManager.releaseHelper();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return this.createMenu(R.menu.page_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return SetupElement.triggerAction(this, item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
