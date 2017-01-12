package net.tlalka.android.fiszki.domain.services;

import android.util.Log;
import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.model.dao.LessonDao;
import net.tlalka.android.fiszki.model.db.DbHelper;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.types.LanguageType;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@SessionScope
public class LessonService {

    private LessonDao lessonDao;

    @Inject
    public LessonService(DbHelper dbHelper) {
        try {
            this.lessonDao = dbHelper.getLessonDao();
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot obtain lesson DAO", ex);
        }
    }

    public Lesson getLesson(long id) {
        try {
            return this.lessonDao.getLessonBy(id);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "Cannot obtain lesson by ID", ex);
            return null;
        }
    }

    public List<Lesson> getLessons(LanguageType languageType) {
        try {
            return this.lessonDao.getLessonsBy(languageType);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "Cannot obtain lesson list", ex);
            return Collections.emptyList();
        }
    }

    public void increaseProgress(Lesson lesson) {
        lesson.setProgress(lesson.getProgress() + 1);
        lessonDao.update(lesson);
    }

    public void updateScore(Lesson lesson, int score) {
        lesson.setScore(score);
        lessonDao.update(lesson);
    }
}
