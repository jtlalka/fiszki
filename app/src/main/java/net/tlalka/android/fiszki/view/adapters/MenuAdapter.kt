package net.tlalka.android.fiszki.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.view.elements.PageElement
import net.tlalka.android.fiszki.view.listeners.MenuListener

class MenuAdapter(context: Context, elements: List<PageElement>) : AbstractAdapter<PageElement>(context, elements) {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val itemView = super.getItemView(convertView, viewGroup, R.layout.main_list_item)
        itemView.tag = itemView.tag ?: ViewHolderPattern(itemView)

        val pageElement = super.getItem(position)
        val viewHolderPattern = itemView.tag as ViewHolderPattern

        viewHolderPattern.button.text = super.getString(pageElement.resourceId)
        viewHolderPattern.button.setOnClickListener(MenuListener(super.context, pageElement))

        return itemView
    }

    private class ViewHolderPattern(view: View) {
        val button: Button = view.findViewById(R.id.button_menu_item)
    }
}
