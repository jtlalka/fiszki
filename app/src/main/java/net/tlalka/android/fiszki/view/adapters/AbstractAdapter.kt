package net.tlalka.android.fiszki.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

abstract class AbstractAdapter<T>(val context: Context, private val elements: List<T>) : BaseAdapter() {

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }

    fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.resources.getString(resId, *formatArgs)
    }

    override fun getCount(): Int {
        return elements.size
    }

    override fun getItem(position: Int): T {
        return elements[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    protected fun getItemView(convertView: View?, viewGroup: ViewGroup?, layoutId: Int) : View {
        return convertView ?: layoutInflater.inflate(layoutId, viewGroup, false)
    }

    abstract override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View
}
