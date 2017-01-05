package net.tlalka.android.fiszki.view.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.dto.WelcomeDto;
import net.tlalka.android.fiszki.model.helpers.StorageHelper;
import net.tlalka.android.fiszki.model.types.StorageType;
import net.tlalka.android.fiszki.view.adapters.MenuAdapter;
import net.tlalka.android.fiszki.view.elements.PageElement;
import net.tlalka.android.fiszki.view.elements.SetupElement;
import net.tlalka.android.fiszki.view.navigations.Navigator;

import javax.inject.Inject;
import java.util.Arrays;

public class MainActivity extends AbstractActivity {

    @Inject
    protected StorageHelper storageHelper;

    @Inject
    protected Navigator navigator;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.main_activity);
        super.getActivityComponent().inject(this);

        this.initStartActivity();
        this.initMenuListActivity();
    }

    private void initStartActivity() {
        if (this.storageHelper.getBoolean(StorageType.WELCOME_VIEW, true)) {
            this.storageHelper.setBoolean(StorageType.WELCOME_VIEW, false);
            this.openStartActivity();
        }
    }

    private void openStartActivity() {
        WelcomeDto welcomeDto = new WelcomeDto(super.getString(R.string.welcome_activity_message));
        navigator.openWelcomeActivity(this, welcomeDto);
    }

    private void initMenuListActivity() {
        ListView listView = (ListView) findViewById(R.id.list_view_menu);
        listView.setAdapter(new MenuAdapter(this, Arrays.asList(PageElement.values())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.createMenu(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return SetupElement.triggerAction(this, item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
