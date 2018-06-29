package net.tlalka.fiszki.model.helpers

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.Gson
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.util.LinkedList

class AssetsHelper(context: Context) {

    private val assetManager: AssetManager = context.assets
    private val gson: Gson = Gson()

    fun getJsonList(assetPath: String): List<String> {
        val fileList = LinkedList<String>()
        for (fileName in assetManager.list(assetPath)) {
            fileList.add(assetPath + File.separator + fileName)
        }
        return fileList
    }

    fun <E> getJson(assetPath: String, jsonDtoClass: Class<E>): E {
        return this.getJson(assetManager.open(assetPath), jsonDtoClass)
    }

    private fun <E> getJson(stream: InputStream, jsonDtoClass: Class<E>): E {
        return stream.use { this.getJson(InputStreamReader(it, "UTF-8"), jsonDtoClass) }
    }

    private fun <E> getJson(reader: Reader, jsonDtoClass: Class<E>): E {
        return reader.use { this.gson.fromJson(it, jsonDtoClass) }
    }
}
