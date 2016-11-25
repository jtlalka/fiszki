package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LessonsListAdapter;
import net.tlalka.android.fiszki.elements.LessonElement;

public class LessonsListActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lessons_list_view);

        this.initLessonsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu, R.menu.main_menu);
    }

    private void initLessonsList() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new LessonsListAdapter(this, LessonElement.getKeys()));
    }
}
