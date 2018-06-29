package net.tlalka.android.fiszki.domain.utils

object ValidUtils {

    fun isNull(value: Any?): Boolean {
        return value == null
    }

    fun isNotNull(value: Any?): Boolean {
        return value != null
    }

    fun isTrue(value: Boolean?): Boolean {
        return value == true
    }

    fun isFalse(value: Boolean?): Boolean {
        return value == false
    }

    fun isEmpty(value: String?): Boolean {
        return value?.length == 0
    }

    fun isEmpty(value: List<*>?): Boolean {
        return value?.isEmpty() ?: false
    }

    fun isEmpty(value: Map<*, *>?): Boolean {
        return value?.isEmpty() ?: false
    }

    fun isNotEmpty(value: String?): Boolean {
        return value?.isNotBlank() ?: false
    }

    fun isNotEmpty(value: List<*>?): Boolean {
        return value?.isNotEmpty() ?: false
    }

    fun isNotEmpty(value: Map<*, *>?): Boolean {
        return value?.isNotEmpty() ?: false
    }
}
