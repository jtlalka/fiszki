package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.OptionsElement;

public class WordsActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.words_view);
    }

    public void onViewClick(View view) {
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return this.createMenu(R.menu.page_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return OptionsElement.triggerAction(this, item.getItemId()) || super.onOptionsItemSelected(item);
    }
}
