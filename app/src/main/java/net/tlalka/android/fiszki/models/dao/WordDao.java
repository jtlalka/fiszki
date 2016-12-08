package net.tlalka.android.fiszki.models.dao;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.models.entities.Word;
import net.tlalka.android.fiszki.models.types.LanguageType;

import java.sql.SQLException;
import java.util.List;

public class WordDao extends AbstractDao<Word, Long> {

    public WordDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Word.class);
    }

    public List<Word> getWordsBy(long lessonId) throws SQLException {
        return super.queryBuilder()
                .distinct()
                .orderBy("progress", true)
                .where()
                .like("lessonId", lessonId)
                .query();
    }

    public Word getWordBy(Word word, LanguageType languageType) throws SQLException {
        return super.queryBuilder()
                .where()
                .eq("clusterId", word.getClusterId())
                .and()
                .eq("languageType", languageType)
                .queryForFirst();
    }
}
