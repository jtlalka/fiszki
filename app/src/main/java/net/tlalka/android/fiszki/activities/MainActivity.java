package net.tlalka.android.fiszki.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.MenuAdapter;
import net.tlalka.android.fiszki.elements.MenuElement;

public class MainActivity extends AbstractActivity {

    public static final String PREFS_NAME = "net.tlalka.android.fiszki.main.preferences";
    public static final String PREFS_KEY = "show.welcome.view";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.menu_view);

        this.initStartActivity();
        this.initMenuListActivity();
    }

    private void initStartActivity() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean showStartActivity = settings.getBoolean(PREFS_KEY, true);

        if (showStartActivity) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(PREFS_KEY, false);
            editor.apply();

            this.openStartActivity();
        }
    }

    private void openStartActivity() {
        Bundle bundle = new Bundle();
        bundle.putString(StartActivity.MESSAGE, getString(R.string.activity_start_info));
        startActivity(StartActivity.class, bundle);
    }

    private void initMenuListActivity() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new MenuAdapter(this, MenuElement.getKeys()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, R.menu.main_menu);
    }
}
