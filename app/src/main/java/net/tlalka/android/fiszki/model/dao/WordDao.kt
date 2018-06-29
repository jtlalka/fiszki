package net.tlalka.android.fiszki.model.dao

import com.j256.ormlite.support.ConnectionSource
import net.tlalka.android.fiszki.model.entities.Cluster
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word
import net.tlalka.android.fiszki.model.types.LanguageType
import java.sql.SQLException

class WordDao @Throws(SQLException::class)
constructor(connectionSource: ConnectionSource) : AbstractDao<Word, Long>(connectionSource, Word::class.java) {

    @Throws(SQLException::class)
    fun getWordBy(cluster: Cluster, languageType: LanguageType): Word {
        return super.queryBuilder()
                .where()
                .eq("cluster_id", cluster)
                .and()
                .eq("languageType", languageType)
                .queryForFirst()
    }

    @Throws(SQLException::class)
    fun getWordsBy(lesson: Lesson): List<Word> {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("lesson_id", lesson)
                .query()
    }

    @Throws(SQLException::class)
    fun getWordsBy(cluster: Cluster): List<Word> {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("cluster_id", cluster)
                .query()
    }

    @Throws(SQLException::class)
    fun getWordsBy(languageType: LanguageType): List<Word> {
        return super.queryBuilder()
                .orderBy("value", true)
                .where()
                .eq("languageType", languageType)
                .query()
    }
}
