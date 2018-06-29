package net.tlalka.fiszki.model.dao

import android.util.Log
import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource
import java.sql.SQLException

abstract class AbstractDao<T, ID>(connectionSource: ConnectionSource, dataClass: Class<T>)
    : BaseDaoImpl<T, ID>(connectionSource, dataClass) {

    override fun create(data: T): Int {
        return optional { super.create(data) }
    }

    override fun create(data: Collection<T>): Int {
        return optional { super.create(data) }
    }

    override fun update(data: T): Int {
        return optional { super.update(data) }
    }

    override fun delete(data: T): Int {
        return optional { super.delete(data) }
    }

    private inline fun optional(sqlQuery: () -> Int): Int {
        return try {
            sqlQuery()
        } catch (ex: SQLException) {
            Log.e(javaClass.name, "Cannot obtain data from repository.", ex)
            0
        }
    }
}
