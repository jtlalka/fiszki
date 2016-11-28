package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LessonListAdapter;
import net.tlalka.android.fiszki.elements.LessonElement;
import net.tlalka.android.fiszki.elements.OptionsElement;

public class LessonListActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lesson_list_view);

        this.initLessonsList();
    }

    private void initLessonsList() {
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new LessonListAdapter(this, LessonElement.getKeys()));
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
