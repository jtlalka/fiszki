package net.tlalka.fiszki.core.modules

import android.content.Context

import dagger.Module
import dagger.Provides
import net.tlalka.fiszki.core.annotations.SessionScope
import net.tlalka.fiszki.model.dao.ClusterDao
import net.tlalka.fiszki.model.dao.LessonDao
import net.tlalka.fiszki.model.dao.WordDao
import net.tlalka.fiszki.model.db.DbHelper
import net.tlalka.fiszki.model.db.DbManager
import net.tlalka.fiszki.model.helpers.AssetsHelper
import net.tlalka.fiszki.model.helpers.StorageHelper

@Module
class SessionModule(@get:Provides @get:SessionScope val context: Context) {

    @Provides
    @SessionScope
    fun getDbHelper(): DbHelper {
        return DbManager.getHelper(context)
    }

    @Provides
    @SessionScope
    fun getLessonDao(dbHelper: DbHelper): LessonDao {
        return dbHelper.getLessonDao()
    }

    @Provides
    @SessionScope
    fun getClusterDao(dbHelper: DbHelper): ClusterDao {
        return dbHelper.getClusterDao()
    }

    @Provides
    @SessionScope
    fun getWordDao(dbHelper: DbHelper): WordDao {
        return dbHelper.getWordDao()
    }

    @Provides
    @SessionScope
    fun getStorageHelper(): StorageHelper {
        return StorageHelper(context)
    }

    @Provides
    @SessionScope
    fun getAssetsHelper(): AssetsHelper {
        return AssetsHelper(context)
    }
}
