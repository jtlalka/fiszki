package net.tlalka.android.fiszki.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.model.entities.Cluster;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.types.LanguageType;

public class WordDao extends AbstractDao<Word, Long> {

    public WordDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Word.class);
    }

    public Word getWordBy(Cluster cluster, LanguageType languageType) throws SQLException {
        return super.queryBuilder()
                .where()
                .eq("cluster_id", cluster)
                .and()
                .eq("languageType", languageType)
                .queryForFirst();
    }

    public List<Word> getWordsBy(Lesson lesson) throws SQLException {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("lesson_id", lesson)
                .query();
    }

    public List<Word> getWordsBy(Cluster cluster) throws SQLException {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("cluster_id", cluster)
                .query();
    }

    public List<Word> getWordsBy(LanguageType languageType) throws SQLException {
        return super.queryBuilder()
                .orderBy("value", true)
                .where()
                .eq("languageType", languageType)
                .query();
    }
}
