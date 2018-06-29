package net.tlalka.android.fiszki.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.controllers.ListController
import net.tlalka.android.fiszki.model.entities.Lesson
import net.tlalka.android.fiszki.model.entities.Word

class WordsAdapter(private val context: Context, listController: ListController) : BaseExpandableListAdapter() {

    private val lessons: List<Lesson> = listController.getLessonList()
    private val words: Map<Long, List<Word>> = listController.getWordsMap()
    private val translations: Map<Long, Word> = listController.getTranslationMap()

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(this.context)
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, view: View?, parent: ViewGroup): View {
        val itemView = view ?: layoutInflater.inflate(R.layout.words_list_group, parent, false)

        val lesson = getGroup(groupPosition)
        val lessonName = itemView.findViewById<TextView>(R.id.word_list_lesson)
        lessonName.text = lesson.name

        return itemView
    }

    override fun getGroup(groupPosition: Int): Lesson {
        return lessons[groupPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return lessons.size
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLast: Boolean, view: View?, parent: ViewGroup): View {
        val viewItem = view ?: layoutInflater.inflate(R.layout.words_list_item, parent, false)

        val word = getChild(groupPosition, childPosition)
        val translation = translations[word.cluster.id]
        val name = viewItem.findViewById<TextView>(R.id.word_list_name)
        val value = viewItem.findViewById<TextView>(R.id.word_list_value)

        name.text = word.value
        value.text = translation?.value

        return viewItem
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Word {
        return words[getGroup(groupPosition).id]?.get(childPosition) ?: Word()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return words[getGroup(groupPosition).id]?.size ?: 0
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }
}
