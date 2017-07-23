package net.tlalka.android.fiszki.domain.services;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import android.util.Log;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import net.tlalka.android.fiszki.core.annotations.SessionScope;
import net.tlalka.android.fiszki.model.dao.WordDao;
import net.tlalka.android.fiszki.model.db.DbHelper;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;

@SessionScope
public class WordService {

    private WordDao wordDao;

    @Inject
    public WordService(DbHelper dbHelper) {
        try {
            this.wordDao = dbHelper.getWordDao();
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot obtain word DAO", ex);
        }
    }

    public List<Word> getWords(Lesson lesson) {
        try {
            return this.wordDao.getWordsBy(lesson);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "Cannot obtain word list by lesson", ex);
            return Collections.emptyList();
        }
    }

    public List<Word> getWords(LanguageType languageType) {
        try {
            return this.wordDao.getWordsBy(languageType);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "Cannot obtain word list by language", ex);
            return Collections.emptyList();
        }
    }

    public Word getTranslation(Word word, LanguageType languageType) {
        try {
            return this.wordDao.getWordBy(word.getCluster(), languageType);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "Cannot obtain word translation", ex);
            return null;
        }
    }

    public List<LanguageType> getLanguages(Word word) {
        try {
            return Stream.of(this.wordDao.getWordsBy(word.getCluster()))
                    .map(Word::getLanguageType)
                    .collect(Collectors.toList());

        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "Cannot obtain language list", ex);
            return Collections.emptyList();
        }
    }

    public void increaseProgress(Word word) {
        word.setProgress(word.getProgress() + 1);
        wordDao.update(word);
    }

    public void decreaseProgress(Word word) {
        word.setProgress(word.getProgress() - 1);
        wordDao.update(word);
    }
}
