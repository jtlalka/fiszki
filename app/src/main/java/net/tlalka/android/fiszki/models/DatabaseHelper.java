package net.tlalka.android.fiszki.models;

import java.sql.SQLException;

import net.tlalka.android.fiszki.elements.LessonElement;
import net.tlalka.android.fiszki.elements.WordElement;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entity.LessonEntity;
import net.tlalka.android.fiszki.models.entity.WordEntity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String DATABASE_NAME = "fiszki.db";
	public static final int DATABASE_VERSION = 11;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, LessonEntity.class);
			TableUtils.createTable(connectionSource, WordEntity.class);
			
			this.initLessonTable();
			this.initWordTable();
			
		} catch (SQLException ex) {
			throw new RuntimeException("Can't create database", ex);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, LessonEntity.class, true);
			TableUtils.dropTable(connectionSource, WordEntity.class, true);
			
			this.onCreate(db, connectionSource);

		} catch (SQLException ex) {
			throw new RuntimeException("Can't drop databases", ex);
		}
	}

	private void initLessonTable() throws SQLException {
		LessonDao pLessonDao = this.getLessonDao();

		for (LessonElement val : LessonElement.values()) {
			pLessonDao.create(new LessonEntity(val.getName(), val.getDesc(), LessonEntity.PROGRESS_BASE, LessonEntity.STATUS_APP));
		}
	}

	private void initWordTable() throws SQLException {
		WordDao pWordDao = this.getWordDao();

		for (WordElement val : WordElement.values()) {
			pWordDao.create(new WordEntity(val.getLessonName(), val.getWordEN(), val.getWordPL(), WordEntity.PROGRESS_BASE));
		}
	}

	public LessonDao getLessonDao() throws SQLException {
		return super.getDao(LessonEntity.class);
	}

	public WordDao getWordDao() throws SQLException {
		return super.getDao(WordEntity.class);
	}
}
