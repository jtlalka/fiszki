package net.tlalka.android.fiszki.models.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.models.dao.ClusterDao;
import net.tlalka.android.fiszki.models.dao.LessonDao;
import net.tlalka.android.fiszki.models.dao.WordDao;
import net.tlalka.android.fiszki.models.entities.Cluster;
import net.tlalka.android.fiszki.models.entities.Lesson;
import net.tlalka.android.fiszki.models.entities.Word;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "fiszki.db";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        new DbCreator(this).execute(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        new DbUpdater(this).execute(connectionSource);
    }

    public LessonDao getLessonDao() throws SQLException {
        return super.getDao(Lesson.class);
    }

    public ClusterDao getClusterDao() throws SQLException {
        return super.getDao(Cluster.class);
    }

    public WordDao getWordDao() throws SQLException {
        return super.getDao(Word.class);
    }
}
