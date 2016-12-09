package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.listeners.LessonsListener;
import net.tlalka.android.fiszki.models.entities.Lesson;

import java.util.List;

public class LessonsAdapter extends AbstractAdapter<Lesson> {

    public LessonsAdapter(Context context, List<Lesson> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lessons_list_item, viewGroup, false);
        }

        Lesson lesson = super.getItem(position);
        ViewHolderPattern viewHolderPattern = new ViewHolderPattern();

        convertView.setTag(viewHolderPattern);
        convertView.setOnClickListener(new LessonsListener(context, lesson));

        viewHolderPattern.name = (TextView) convertView.findViewById(R.id.text_view_name);
        viewHolderPattern.name.setText(getString(R.string.list_item, position + 1, lesson.getName()));

        viewHolderPattern.desc = (TextView) convertView.findViewById(R.id.text_view_desc);
        viewHolderPattern.desc.setText(lesson.getLevelType().name());

        return convertView;
    }

    private static class ViewHolderPattern {
        public TextView name;
        public TextView desc;
    }
}
