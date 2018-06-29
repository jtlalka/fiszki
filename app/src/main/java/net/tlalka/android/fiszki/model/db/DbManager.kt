package net.tlalka.android.fiszki.model.db

import android.content.Context

import com.j256.ormlite.android.apptools.OpenHelperManager

object DbManager {

    fun getHelper(context: Context): DbHelper {
        return OpenHelperManager.getHelper(context, DbHelper::class.java)
    }

    fun releaseHelper() {
        OpenHelperManager.releaseHelper()
    }
}
