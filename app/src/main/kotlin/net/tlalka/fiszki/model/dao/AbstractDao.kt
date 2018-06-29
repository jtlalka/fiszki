package net.tlalka.fiszki.model.dao

import android.util.Log
import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource
import java.sql.SQLException

abstract class AbstractDao<T, ID>(connectionSource: ConnectionSource, dataClass: Class<T>)
        : BaseDaoImpl<T, ID>(connectionSource, dataClass) {

    override fun create(data: T): Int {
        return try {
            super.create(data)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "DB create exception", ex)
            0
        }
    }

    override fun create(data: Collection<T>): Int {
        return try {
            super.create(data)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "DB create exception", ex)
            0
        }
    }

    override fun update(data: T): Int {
        return try {
            super.update(data)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "DB update exception", ex)
            0
        }
    }

    override fun delete(data: T): Int {
        return try {
            super.delete(data)
        } catch (ex: SQLException) {
            Log.e(this.javaClass.name, "DB delete exception", ex)
            0
        }
    }
}
