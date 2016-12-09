package net.tlalka.android.fiszki.models.dao;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.models.entities.Cluster;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;

import java.sql.SQLException;
import java.util.List;

public class WordDao extends AbstractDao<Word, Long> {

    public WordDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Word.class);
    }

    public List<Word> getWordsBy(Lesson lesson) throws SQLException {
        return super.queryBuilder()
                .distinct()
                .where()
                .eq("lesson_id", lesson)
                .query();
    }

    public Word getWordBy(Cluster cluster, LanguageType languageType) throws SQLException {
        return super.queryBuilder()
                .where()
                .eq("cluster_id", cluster)
                .and()
                .eq("languageType", languageType)
                .queryForFirst();
    }
}
