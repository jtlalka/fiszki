package net.tlalka.android.fiszki.domain.services

import android.util.Log
import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import net.tlalka.android.fiszki.core.annotations.SessionScope
import net.tlalka.android.fiszki.model.dao.WordDao
import net.tlalka.android.fiszki.model.db.DbHelper
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word
import net.tlalka.android.fiszki.model.types.LanguageType
import java.sql.SQLException
import javax.inject.Inject

@SessionScope
class WordService @Inject constructor(dbHelper: DbHelper) {

    private var wordDao: WordDao? = null

    init {
        try {
            this.wordDao = dbHelper.getWordDao()
        } catch (ex: SQLException) {
            throw RuntimeException("Cannot obtain word DAO", ex)
        }
    }

    fun getWords(lesson: Lesson): List<Word> {
        return try {
            this.wordDao!!.getWordsBy(lesson)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain word list by lesson", ex)
            emptyList()
        }
    }

    fun getWords(languageType: LanguageType): List<Word> {
        return try {
            this.wordDao!!.getWordsBy(languageType)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain word list by language", ex)
            emptyList()
        }
    }

    fun getTranslation(word: Word, languageType: LanguageType): Word {
        return try {
            this.wordDao!!.getWordBy(word.cluster, languageType)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain word translation", ex)
            Word()
        }
    }

    fun getLanguages(word: Word): List<LanguageType> {
        return try {
            Stream.of(this.wordDao!!.getWordsBy(word.cluster))
                    .map<LanguageType>{ it.languageType }
                    .collect(Collectors.toList())

        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "Cannot obtain language list", ex)
            emptyList()
        }
    }

    fun increaseProgress(word: Word) {
        word.progress = word.progress + 1
        wordDao!!.update(word)
    }

    fun decreaseProgress(word: Word) {
        word.progress = word.progress - 1
        wordDao!!.update(word)
    }
}
