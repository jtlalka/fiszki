package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LessonsAdapter;
import net.tlalka.android.fiszki.elements.LessonElement;

public class LessonsActivity extends BasePageActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lessons_activity);

        this.initLessonsList();
    }

    private void initLessonsList() {
        ListView listView = (ListView) findViewById(R.id.list_view_lessons);
        listView.setAdapter(new LessonsAdapter(this, LessonElement.getKeys()));
    }
}
