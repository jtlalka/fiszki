package net.tlalka.android.fiszki.activities;

import android.view.Menu;
import android.view.MenuItem;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.SetupElement;
import net.tlalka.android.fiszki.models.DbHelper;
import net.tlalka.android.fiszki.models.DbManager;
import net.tlalka.android.fiszki.utils.ValidUtils;

public abstract class BasePageActivity extends AbstractActivity {

    private DbHelper dbHelper;

    protected DbHelper getDbHelper() {
        if (ValidUtils.isNull(this.dbHelper)) {
            this.dbHelper = DbManager.getHelper(this);
        }
        return this.dbHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ValidUtils.isNotNull(dbHelper)) {
            DbManager.releaseHelper();
        }
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
