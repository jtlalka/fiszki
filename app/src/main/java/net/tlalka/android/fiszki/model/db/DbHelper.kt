package net.tlalka.android.fiszki.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import net.tlalka.android.fiszki.model.dao.ClusterDao
import net.tlalka.android.fiszki.model.dao.LessonDao
import net.tlalka.android.fiszki.model.dao.WordDao
import net.tlalka.android.fiszki.model.entities.Cluster
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word
import net.tlalka.android.fiszki.model.helpers.AssetsHelper
import java.sql.SQLException

class DbHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val assetsHelper: AssetsHelper = AssetsHelper(context)

    override fun onCreate(db: SQLiteDatabase, connectionSource: ConnectionSource) {
        DbCreator(this, assetsHelper).execute(connectionSource)
    }

    override fun onUpgrade(db: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        DbUpdater(this, assetsHelper).execute(connectionSource)
    }

    @Throws(SQLException::class)
    fun getLessonDao(): LessonDao = super.getDao(Lesson::class.java)

    @Throws(SQLException::class)
    fun getClusterDao(): ClusterDao = super.getDao(Cluster::class.java)

    @Throws(SQLException::class)
    fun getWordDao(): WordDao = super.getDao(Word::class.java)

    companion object {
        const val DATABASE_NAME = "fiszki.db"
        const val DATABASE_VERSION = 1
    }
}