package net.tlalka.android.fiszki.models.dao;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.models.entities.LessonEntity;

import java.sql.SQLException;
import java.util.List;

public class LessonDao extends AbstractDao<LessonEntity, Long> {

    public LessonDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, LessonEntity.class);
    }

    public LessonEntity getLessonByLessonName(String lessonName) throws SQLException {
        return super.queryBuilder()
                .distinct()
                .where()
                .like("name", lessonName)
                .query()
                .get(0);
    }

    public List<LessonEntity> getAll() throws SQLException {
        return super.queryForAll();
    }
}
