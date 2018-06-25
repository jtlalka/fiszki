package net.tlalka.android.fiszki.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.model.entities.Lesson

class LessonsAdapter(context: Context, elements: List<Lesson>) : AbstractAdapter<Lesson>(context, elements) {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val itemView = super.getItemView(convertView, viewGroup, R.layout.lesson_list_item)
        itemView.tag = itemView.tag ?: ViewHolderPattern(itemView)

        val lesson = super.getItem(position)
        val viewHolderPattern = itemView.tag as ViewHolderPattern

        viewHolderPattern.icon.setImageResource(getIconId(lesson.progress))
        viewHolderPattern.name.text = getString(R.string.list_item, position + 1, lesson.name)
        viewHolderPattern.desc.text = lesson.levelType.name

        return itemView
    }

    private fun getIconId(progress: Int): Int {
        return if (progress > 0) R.drawable.lesson_item_checked else R.drawable.lesson_item_empty
    }

    private class ViewHolderPattern(view: View) {
        val icon: ImageView = view.findViewById(R.id.lesson_list_icon)
        val name: TextView = view.findViewById(R.id.lesson_list_name)
        val desc: TextView = view.findViewById(R.id.lesson_list_desc)
    }
}
