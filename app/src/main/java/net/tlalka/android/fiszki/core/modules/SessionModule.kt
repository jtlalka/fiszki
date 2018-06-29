package net.tlalka.android.fiszki.core.modules

import android.content.Context

import dagger.Module
import dagger.Provides
import net.tlalka.android.fiszki.core.annotations.SessionScope
import net.tlalka.android.fiszki.model.db.DbHelper
import net.tlalka.android.fiszki.model.db.DbManager
import net.tlalka.android.fiszki.model.helpers.AssetsHelper
import net.tlalka.android.fiszki.model.helpers.StorageHelper

@Module
class SessionModule(@get:Provides
                    @get:SessionScope
                    val context: Context) {

    @Provides
    @SessionScope
    fun getDbHelper(): DbHelper {
        return DbManager.getHelper(this.context)
    }

    @Provides
    @SessionScope
    fun getStorageHelper(): StorageHelper {
        return StorageHelper(this.context)
    }

    @Provides
    @SessionScope
    fun getAssetsHelper(): AssetsHelper {
        return AssetsHelper(this.context)
    }
}
