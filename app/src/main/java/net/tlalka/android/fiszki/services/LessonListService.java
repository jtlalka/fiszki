package net.tlalka.android.fiszki.services;

import android.util.Log;
import net.tlalka.android.fiszki.injections.annotations.ActivityScope;
import net.tlalka.android.fiszki.models.db.DbHelper;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.types.LanguageType;
import net.tlalka.android.fiszki.models.types.StorageType;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@ActivityScope
public class LessonListService {

    @Inject
    StorageHelper storageHelper;

    @Inject
    DbHelper dbHelper;

    @Inject
    public LessonListService() {
    }

    public List<Lesson> getLessons() {
        LanguageType language = storageHelper.getEnum(StorageType.LANGUAGE, LanguageType.EN);
        try {
            return dbHelper.getLessonDao().getLessonsBy(language);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "SQL data exception", ex);
            return Collections.emptyList();
        }
    }
}
