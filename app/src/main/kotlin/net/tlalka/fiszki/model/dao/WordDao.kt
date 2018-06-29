package net.tlalka.fiszki.model.dao

import com.j256.ormlite.support.ConnectionSource
import net.tlalka.fiszki.model.entities.Cluster
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.entities.Word
import net.tlalka.fiszki.model.types.LanguageType

class WordDao constructor(connectionSource: ConnectionSource)
    : AbstractDao<Word, Long>(connectionSource, Word::class.java) {

    fun getWordBy(cluster: Cluster, languageType: LanguageType): Word {
        return super.queryBuilder()
                .where()
                .eq("cluster_id", cluster)
                .and()
                .eq("languageType", languageType)
                .queryForFirst()
    }

    fun getWordsBy(lesson: Lesson): List<Word> {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("lesson_id", lesson)
                .query()
    }

    fun getWordsBy(cluster: Cluster): List<Word> {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("cluster_id", cluster)
                .query()
    }

    fun getWordsBy(languageType: LanguageType): List<Word> {
        return super.queryBuilder()
                .orderBy("value", true)
                .where()
                .eq("languageType", languageType)
                .query()
    }
}
