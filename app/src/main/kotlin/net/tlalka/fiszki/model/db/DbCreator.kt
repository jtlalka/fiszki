package net.tlalka.fiszki.model.db

import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import net.tlalka.fiszki.model.dto.json.LessonDto
import net.tlalka.fiszki.model.dto.json.WordDto
import net.tlalka.fiszki.model.entities.Cluster
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.entities.Word
import net.tlalka.fiszki.model.helpers.AssetsHelper
import net.tlalka.fiszki.model.types.LanguageType
import net.tlalka.fiszki.model.types.OwnerType
import java.io.IOException
import java.sql.SQLException
import java.util.ArrayList
import java.util.HashMap

class DbCreator(private val dbHelper: DbHelper, private val assetsHelper: AssetsHelper) {

    fun execute(connectionSource: ConnectionSource) {
        try {
            TableUtils.createTable(connectionSource, Lesson::class.java)
            TableUtils.createTable(connectionSource, Cluster::class.java)
            TableUtils.createTable(connectionSource, Word::class.java)

            this.insertApplicationData()
            this.insertConfigurationData()

        } catch (ex: SQLException) {
            throw RuntimeException("Can't create database", ex)
        }
    }

    private fun insertApplicationData() {
        // not implemented yet.
    }

    private fun insertConfigurationData() {
        try {
            for (fileName in assetsHelper.getJsonList(FILE_PATH)) {
                insertLessonsData(assetsHelper.getJson(fileName, LessonDto::class.java))
            }
        } catch (ex: IOException) {
            throw RuntimeException("Reading initial json data error.", ex)
        }
    }

    private fun insertLessonsData(lessonDto: LessonDto) {
        val lessonMap = HashMap<LanguageType, Lesson>()
        val lessonNames = lessonDto.name

        for (languageType in lessonNames.getLanguages()) {
            val name = lessonNames[languageType] ?: ""
            val lesson = Lesson(name, lessonDto.level, languageType)

            lessonMap[languageType] = lesson
        }
        dbHelper.getLessonDao().create(lessonMap.values)
        insertWordsData(lessonDto.words, lessonMap)
    }

    private fun insertWordsData(wordsDto: List<WordDto>, lessonMap: Map<LanguageType, Lesson>) {
        for (wordDto in wordsDto) {
            val words = ArrayList<Word>()
            val cluster = insertClusterData()

            for (languageType in wordDto.getLanguages()) {
                val value = wordDto[languageType]!!
                val lesson = lessonMap[languageType]!!
                words.add(Word(value, cluster, lesson, languageType))
            }
            dbHelper.getWordDao().create(words)
        }
    }

    private fun insertClusterData(): Cluster {
        val cluster = Cluster(OwnerType.SYSTEM)
        dbHelper.getClusterDao().create(cluster)
        return cluster
    }

    companion object {
        private const val FILE_PATH = "lessons"
    }
}
