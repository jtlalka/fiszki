package net.tlalka.android.fiszki.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.adapters.LessonsAdapter;
import net.tlalka.android.fiszki.listeners.LessonsListener;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class LessonsActivity extends BasePageActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lessons_activity);

        this.initLessonsList();
    }

    private void initLessonsList() {
        ListView listView = (ListView) findViewById(R.id.list_view_lessons);
        listView.setAdapter(new LessonsAdapter(this, getLessons()));
        listView.setOnItemClickListener(new LessonsListener(this));
    }

    private List<Lesson> getLessons() {
        LanguageType language = super.getStorageHelper().getEnum(StorageType.LANGUAGE, LanguageType.EN);

        try {
            return super.getDbHelper().getLessonDao().getLessonsBy(language);
        } catch (SQLException ex) {
            Log.e(this.getLocalClassName(), "SQL data exception", ex);
            return Collections.emptyList();
        }
    }
}
