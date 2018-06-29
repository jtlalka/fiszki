package net.tlalka.fiszki.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import net.tlalka.fiszki.model.dao.ClusterDao
import net.tlalka.fiszki.model.dao.LessonDao
import net.tlalka.fiszki.model.dao.WordDao
import net.tlalka.fiszki.model.entities.Cluster
import net.tlalka.fiszki.model.entities.Lesson
import net.tlalka.fiszki.model.entities.Word
import net.tlalka.fiszki.model.helpers.AssetsHelper

class DbHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val assetsHelper: AssetsHelper = AssetsHelper(context)

    override fun onCreate(db: SQLiteDatabase, connectionSource: ConnectionSource) {
        DbCreator(this, assetsHelper).execute(connectionSource)
    }

    override fun onUpgrade(db: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        DbUpdater(this, assetsHelper).execute(connectionSource)
    }

    fun getLessonDao(): LessonDao = super.getDao(Lesson::class.java)

    fun getClusterDao(): ClusterDao = super.getDao(Cluster::class.java)

    fun getWordDao(): WordDao = super.getDao(Word::class.java)

    companion object {
        const val DATABASE_NAME = "fiszki.db"
        const val DATABASE_VERSION = 1
    }
}
