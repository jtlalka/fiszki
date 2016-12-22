package net.tlalka.android.fiszki.services;

import android.util.Log;
import net.tlalka.android.fiszki.injections.annotations.ActivityScope;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.db.DbHelper;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;

import javax.inject.Inject;
import java.util.List;

@ActivityScope
public class LessonService {

    private LessonDao lessonDao;
    private WordDao wordDao;
    private LessonDto lessonDto;

    private List<Word> words;
    private Lesson lesson;
    private Word word;
    private LanguageType translation;


    @Inject
    public LessonService(DbHelper dbHelper) {

    }

    public void runService() {
        Log.e(LessonListService.class.getName(), "BBBBBBBBB");
    }
}
