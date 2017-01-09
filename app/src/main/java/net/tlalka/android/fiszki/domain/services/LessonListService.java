package net.tlalka.android.fiszki.domain.services;

import android.util.Log;
import net.tlalka.android.fiszki.core.annotations.ActivityScope;
import net.tlalka.android.fiszki.model.db.DbHelper;
import net.tlalka.android.fiszki.model.entities.Lesson;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@ActivityScope
public class LessonListService {

    @Inject
    StorageService storageService;

    @Inject
    DbHelper dbHelper;

    @Inject
    public LessonListService() {
    }

    public List<Lesson> getLessons() {
        try {
            return dbHelper.getLessonDao().getLessonsBy(storageService.getLanguage());
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "SQL data exception", ex);
            return Collections.emptyList();
        }
    }
}
