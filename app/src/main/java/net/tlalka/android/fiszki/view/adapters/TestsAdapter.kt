package net.tlalka.android.fiszki.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.model.entities.Lesson

class TestsAdapter(context: Context, elements: List<Lesson>) : AbstractAdapter<Lesson>(context, elements) {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val itemView = super.getItemView(convertView, viewGroup, R.layout.test_list_item)
        itemView.tag = itemView.tag ?: ViewHolderPattern(itemView)

        val lesson = super.getItem(position)
        val viewHolderPattern = itemView.tag as ViewHolderPattern

        viewHolderPattern.score.text = getString(R.string.test_score_value, lesson.score)
        viewHolderPattern.name.text = getString(R.string.list_item, position + 1, lesson.name)
        viewHolderPattern.desc.text = lesson.levelType.name

        return itemView
    }

    private class ViewHolderPattern(view: View) {
        val icon: ImageView = view.findViewById<View>(R.id.test_list_icon) as ImageView
        val score: TextView = view.findViewById<View>(R.id.test_list_score) as TextView
        val name: TextView = view.findViewById<View>(R.id.test_list_name) as TextView
        val desc: TextView = view.findViewById<View>(R.id.test_list_desc) as TextView
    }
}
