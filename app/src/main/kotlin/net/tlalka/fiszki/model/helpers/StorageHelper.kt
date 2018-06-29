package net.tlalka.fiszki.model.helpers

import android.content.Context
import android.content.SharedPreferences

class StorageHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = this.getSharedPreferences(context)

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)
    }

    fun getBoolean(key: Enum<*>, defValue: Boolean): Boolean {
        return this.sharedPreferences.getBoolean(key.name, defValue)
    }

    fun setBoolean(key: Enum<*>, value: Boolean) {
        this.sharedPreferences.edit().putBoolean(key.name, value).apply()
    }

    fun getValue(key: Enum<*>, defValue: Long): Long {
        return this.sharedPreferences.getLong(key.name, defValue)
    }

    fun setValue(key: Enum<*>, value: Long) {
        this.sharedPreferences.edit().putLong(key.name, value).apply()
    }

    fun getString(key: Enum<*>, defValue: String): String {
        return this.sharedPreferences.getString(key.name, defValue) ?: ""
    }

    fun setString(key: Enum<*>, value: String) {
        this.sharedPreferences.edit().putString(key.name, value).apply()
    }

    inline fun <reified E : Enum<E>> getEnum(key: Enum<*>, defValue: Enum<E>): E {
        return enumValueOf(this.getString(key, defValue.name))
    }

    fun <E : Enum<E>> setEnum(key: Enum<*>, value: E) {
        this.sharedPreferences.edit().putString(key.name, value.name).apply()
    }
}
