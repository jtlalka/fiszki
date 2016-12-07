package net.tlalka.android.fiszki.models.dao;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.models.entities.WordEntity;

import java.sql.SQLException;
import java.util.List;

public class WordDao extends AbstractDao<WordEntity, Long> {

    public WordDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, WordEntity.class);
    }

    public List<WordEntity> getWordsByLessonName(String lessonName) throws SQLException {
        return super.queryBuilder()
                .distinct()
                .orderBy("progress", true)
                .where()
                .like("lessonName", lessonName)
                .query();
    }

    public List<WordEntity> getAll() throws SQLException {
        return super.queryForAll();
    }
}
