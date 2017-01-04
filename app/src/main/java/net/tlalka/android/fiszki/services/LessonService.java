package net.tlalka.android.fiszki.services;

import android.util.Log;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import net.tlalka.android.fiszki.injections.annotations.ActivityScope;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.db.DbHelper;
import net.tlalka.android.fiszki.models.dto.LessonDto;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ActivityScope
public class LessonService {

    private LessonDao lessonDao;
    private WordDao wordDao;

    private Lesson lesson;
    private List<Word> words;
    private Word activeWord;

    @Inject
    public LessonService(DbHelper dbHelper, LessonDto lessonDto) {
        try {
            this.lessonDao = dbHelper.getLessonDao();
            this.wordDao = dbHelper.getWordDao();

            this.lesson = this.lessonDao.getLessonBy(lessonDto.getLessonId());
            this.words = this.wordDao.getWordsBy(lesson);

        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "SQL data exception", ex);
        }
    }

    public Word getTranslation(LanguageType languageType) {
        try {
            return this.wordDao.getWordBy(activeWord.getCluster(), languageType);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "SQL data exception", ex);
            return null;
        }
    }

    public List<LanguageType> getLanguages() {
        try {
            return Stream.of(this.wordDao.getWordsBy(this.activeWord.getCluster()))
                    .map(Word::getLanguageType)
                    .collect(Collectors.toList());

        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "SQL data exception", ex);
            return Collections.emptyList();
        }
    }

    public boolean hasNextWord() {
        return this.words.size() > 0;
    }

    public String getNextWord() {
        this.activeWord = this.generateWord();
        return this.activeWord.getValue();
    }

    private Word generateWord() {
        Word newWord = words.get(new Random().nextInt(words.size()));
        return (words.size() > 1 && newWord == activeWord) ? generateWord() : newWord;
    }

    public void correctAnswer() {
        this.activeWord.setProgress(this.activeWord.getProgress() + 1);
        this.wordDao.update(this.activeWord);
        this.words.remove(this.activeWord);
    }

    public void incorrectAnswer() {
        this.activeWord.setProgress(this.activeWord.getProgress() - 1);
        this.wordDao.update(this.activeWord);
    }

    public void updateLessonProgress() {
        this.lesson.setProgress(this.lesson.getProgress() + 1);
        this.lessonDao.update(this.lesson);
    }
}
