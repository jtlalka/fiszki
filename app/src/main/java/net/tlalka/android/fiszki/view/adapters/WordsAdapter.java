package net.tlalka.android.fiszki.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import net.tlalka.android.fiszki.model.entities.Lesson;
import net.tlalka.android.fiszki.model.entities.Word;

import java.util.List;
import java.util.Map;

public class WordsAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<Lesson> lessons;
    private final Map<Long, List<Word>> words;
    private final Map<Long, Word> translations;

    public WordsAdapter(Context context, ListController listController) {
        this.context = context;
        this.lessons = listController.getLessonList();
        this.words = listController.getWordsMap();
        this.translations = listController.getTranslationMap();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.words_list_group, parent, false);
        }

        Lesson lesson = getGroup(groupPosition);
        TextView lessonName = (TextView) view.findViewById(R.id.word_list_lesson);
        lessonName.setText(lesson.getName());

        return view;
    }

    @Override
    public Lesson getGroup(int groupPosition) {
        return this.lessons.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return this.lessons.size();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLast, View view, ViewGroup parent) {
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.words_list_item, parent, false);
        }

        Word word = getChild(groupPosition, childPosition);
        Word translation = translations.get(word.getCluster().getId());
        TextView name = (TextView) view.findViewById(R.id.word_list_name);
        TextView value = (TextView) view.findViewById(R.id.word_list_value);

        name.setText(word.getValue());
        value.setText(translation.getValue());

        return view;
    }

    @Override
    public Word getChild(int groupPosition, int childPosition) {
        return this.words.get(this.getGroup(groupPosition).getId()).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.words.get(this.getGroup(groupPosition).getId()).size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(this.context);
    }
}
