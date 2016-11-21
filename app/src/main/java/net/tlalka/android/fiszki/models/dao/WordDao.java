package net.tlalka.android.fiszki.models.dao;

import java.sql.SQLException;
import java.util.List;

import net.tlalka.android.fiszki.models.entity.WordEntity;

import com.j256.ormlite.support.ConnectionSource;

public class WordDao extends AbstractDao<WordEntity, Long> {

    public WordDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, WordEntity.class);
    }

    public List<WordEntity> getWordsByLessnoName(String lessonName) throws SQLException {
    	return super.queryBuilder().distinct().orderBy("progress", true).where().like("lessonName", lessonName).query();
    }

    public List<WordEntity> getAll() throws SQLException {
		return super.queryForAll();
	}
}
