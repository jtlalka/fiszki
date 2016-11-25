package net.tlalka.android.fiszki.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.MenuAdapter;
import net.tlalka.android.fiszki.elements.MenuElement;

public class MainActivity extends AbstractActivity {

    public static final String PREFS_NAME = "StartActivity.show.hello.view";
    public static final String PREFS_VALUE = "showActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.menu_view);
        this.initMenuListActivity();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean showActivity = settings.getBoolean(PREFS_VALUE, false);

        if (!showActivity) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(PREFS_VALUE, true);
            editor.apply();

            this.initStartActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, R.menu.main_menu);
    }

    private void initStartActivity() {
        Bundle bundleToSend = new Bundle();
        bundleToSend.putString(StartActivity.VIEW_INFO, "Click to start!");

        startActivity(StartActivity.class, bundleToSend);
    }

    private void initMenuListActivity() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MenuAdapter(this, MenuElement.getKeys()));
    }
}
