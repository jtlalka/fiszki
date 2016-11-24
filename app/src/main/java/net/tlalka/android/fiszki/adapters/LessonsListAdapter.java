package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.LessonElement;
import net.tlalka.android.fiszki.listeners.LessonsListListener;

import java.util.List;

public class LessonsListAdapter extends AbstractAdapter<String> {

    public LessonsListAdapter(Context context, List<String> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.lessons_list_item, viewGroup, false);

        LessonElement lessonElement = LessonElement.getValue(elements.get(position));
        LessonsListListener lessonsListListener = new LessonsListListener(context, lessonElement);

        ViewHolderPattern viewHolderPattern = new ViewHolderPattern();
        convertView.setTag(viewHolderPattern);
        convertView.setOnClickListener(lessonsListListener);

        viewHolderPattern.name = (TextView) convertView.findViewById(R.id.name);
        viewHolderPattern.name.setText(lessonElement.getName());

        viewHolderPattern.desc = (TextView) convertView.findViewById(R.id.desc);
        viewHolderPattern.desc.setText(lessonElement.getDesc());

        return convertView;
    }

    private static class ViewHolderPattern {
        public TextView name;
        public TextView desc;
    }
}
