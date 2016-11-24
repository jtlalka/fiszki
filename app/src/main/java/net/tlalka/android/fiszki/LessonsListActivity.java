package net.tlalka.android.fiszki;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import net.tlalka.android.fiszki.adapters.LessonsListAdapter;
import net.tlalka.android.fiszki.elements.LessonElement;
import net.tlalka.android.fwork.FworkActivity;

public class LessonsListActivity extends FworkActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.lessons_list_view);
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
