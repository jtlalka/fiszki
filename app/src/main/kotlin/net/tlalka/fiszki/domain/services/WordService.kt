package net.tlalka.fiszki.domain.services

import android.util.Log
import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import net.tlalka.fiszki.core.annotations.SessionScope
import net.tlalka.fiszki.model.dao.WordDao
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.entities.Word
import net.tlalka.fiszki.model.types.LanguageType
import java.sql.SQLException
import javax.inject.Inject

@SessionScope
class WordService @Inject constructor(private val wordDao: WordDao) {

    fun getWords(lesson: Lesson): List<Word> {
        return try {
            wordDao.getWordsBy(lesson)
        } catch (ex: SQLException) {
            Log.e(javaClass.name, "Cannot obtain word list by lesson", ex)
            emptyList()
        }
    }

    fun getWords(languageType: LanguageType): List<Word> {
        return try {
            wordDao.getWordsBy(languageType)
        } catch (ex: SQLException) {
            Log.e(javaClass.name, "Cannot obtain word list by language", ex)
            emptyList()
        }
    }

    fun getTranslation(word: Word, languageType: LanguageType): Word {
        return try {
            wordDao.getWordBy(word.cluster, languageType)
        } catch (ex: SQLException) {
            Log.e(javaClass.name, "Cannot obtain word translation", ex)
            Word()
        }
    }

    fun getLanguages(word: Word): List<LanguageType> {
        return try {
            Stream.of(wordDao.getWordsBy(word.cluster))
                    .map<LanguageType>{ it.languageType }
                    .collect(Collectors.toList())

        } catch (ex: SQLException) {
            Log.e(javaClass.name, "Cannot obtain language list", ex)
            emptyList()
        }
    }

    fun increaseProgress(word: Word) {
        word.progress = word.progress + 1
        wordDao.update(word)
    }

    fun decreaseProgress(word: Word) {
        word.progress = word.progress - 1
        wordDao.update(word)
    }
}
