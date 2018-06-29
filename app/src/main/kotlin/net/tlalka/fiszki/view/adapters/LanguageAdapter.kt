package net.tlalka.fiszki.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.tlalka.fiszki.R
import net.tlalka.fiszki.model.types.LanguageType

class LanguageAdapter(context: Context, languageTypes: List<LanguageType>)
        : AbstractAdapter<LanguageType>(context, languageTypes) {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val itemView = super.getItemView(convertView, viewGroup, R.layout.settings_spinner_item)
        itemView.tag = itemView.tag ?: ViewHolderPattern(itemView)

        val viewHolderPattern = itemView.tag as ViewHolderPattern
        viewHolderPattern.item.text = super.getItem(position).name
        return itemView
    }

    private class ViewHolderPattern(view: View) {
        val item: TextView = view.findViewById(R.id.settings_spinner_item)
    }
}
