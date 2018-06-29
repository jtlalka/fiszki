package net.tlalka.android.fiszki.model.db

import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import net.tlalka.android.fiszki.model.entities.Cluster
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word
import net.tlalka.android.fiszki.model.helpers.AssetsHelper
import java.sql.SQLException

class DbUpdater(private val dbHelper: DbHelper, private val assetsHelper: AssetsHelper) {

    fun execute(connectionSource: ConnectionSource) {
        try {
            TableUtils.dropTable<Word, Any>(connectionSource, Word::class.java, true)
            TableUtils.dropTable<Cluster, Any>(connectionSource, Cluster::class.java, true)
            TableUtils.dropTable<Lesson, Any>(connectionSource, Lesson::class.java, true)

            DbCreator(dbHelper, assetsHelper).execute(connectionSource)

        } catch (ex: SQLException) {
            throw RuntimeException("Can't update databases", ex)
        }
    }
}
