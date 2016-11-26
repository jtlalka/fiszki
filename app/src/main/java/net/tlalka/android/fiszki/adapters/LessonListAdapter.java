package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.LessonElement;
import net.tlalka.android.fiszki.listeners.LessonListListener;

import java.util.List;

public class LessonListAdapter extends AbstractAdapter<String> {

    public LessonListAdapter(Context context, List<String> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lesson_list_item, viewGroup, false);
        }

        LessonElement lessonElement = LessonElement.getValue(super.getItem(position));
        ViewHolderPattern viewHolderPattern = new ViewHolderPattern();

        convertView.setTag(viewHolderPattern);
        convertView.setOnClickListener(new LessonListListener(context, lessonElement));

        viewHolderPattern.name = (TextView) convertView.findViewById(R.id.text_view_name);
        viewHolderPattern.name.setText(lessonElement.getName());

        viewHolderPattern.desc = (TextView) convertView.findViewById(R.id.text_view_desc);
        viewHolderPattern.desc.setText(lessonElement.getDesc());

        return convertView;
    }

    private static class ViewHolderPattern {
        public TextView name;
        public TextView desc;
    }
}
