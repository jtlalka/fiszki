package net.tlalka.android.fiszki.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.MenuAdapter;
import net.tlalka.android.fiszki.elements.PageElement;
import net.tlalka.android.fiszki.elements.SetupElement;
import net.tlalka.android.fiszki.models.dto.WelcomeDto;
import net.tlalka.android.fiszki.models.types.StorageType;
import org.parceler.Parcels;

import java.util.Arrays;

public class MainActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.main_activity);

        this.initStartActivity();
        this.initMenuListActivity();
    }

    private void initStartActivity() {
        if (super.getStorageHelper().getBoolean(StorageType.WELCOME_VIEW, true)) {
            super.getStorageHelper().setBoolean(StorageType.WELCOME_VIEW, false);
            this.openStartActivity();
        }
    }

    private void openStartActivity() {
        String message = super.getString(R.string.welcome_activity_message);
        Parcelable parcelable = Parcels.wrap(new WelcomeDto(message));

        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(WelcomeDto.class.getName(), parcelable);
        startActivity(intent);
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
