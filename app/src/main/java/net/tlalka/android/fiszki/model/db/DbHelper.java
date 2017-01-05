package net.tlalka.android.fiszki.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.model.dao.ClusterDao;
import net.tlalka.android.fiszki.model.dao.LessonDao;
import net.tlalka.android.fiszki.model.dao.WordDao;
import net.tlalka.android.fiszki.model.entities.Cluster;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;
import net.tlalka.android.fiszki.model.helpers.AssetsHelper;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "fiszki.db";
    public static final int DATABASE_VERSION = 1;

    private final AssetsHelper assetsHelper;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.assetsHelper = new AssetsHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        new DbCreator(this, assetsHelper).execute(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        new DbUpdater(this, assetsHelper).execute(connectionSource);
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
