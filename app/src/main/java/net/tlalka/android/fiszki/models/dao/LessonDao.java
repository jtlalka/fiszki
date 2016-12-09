package net.tlalka.android.fiszki.models.dao;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.types.LanguageType;

import java.sql.SQLException;
import java.util.List;

public class LessonDao extends AbstractDao<Lesson, Long> {

    public LessonDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Lesson.class);
    }

    public Lesson getLessonBy(long id) throws SQLException {
        return super.queryForId(id);
    }

    public List<Lesson> getLessonsBy(LanguageType languageType) throws SQLException {
        return super.queryBuilder()
                .orderBy("ownerType", true)
                .orderBy("levelType", true)
                .where()
                .eq("languageType", languageType)
                .query();
    }
}
