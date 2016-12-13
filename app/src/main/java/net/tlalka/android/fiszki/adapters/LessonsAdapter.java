package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.models.entities.Lesson;

import java.util.List;

public class LessonsAdapter extends AbstractAdapter<Lesson> {

    public LessonsAdapter(Context context, List<Lesson> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = super.getLayoutInflater().inflate(R.layout.lessons_list_item, viewGroup, false);
            convertView.setTag(new ViewHolderPattern(convertView));
        }

        Lesson lesson = super.getItem(position);

        ViewHolderPattern viewHolderPattern = (ViewHolderPattern) convertView.getTag();
        viewHolderPattern.name.setText(getString(R.string.list_item, position + 1, lesson.getName()));
        viewHolderPattern.desc.setText(lesson.getLevelType().name());

        return convertView;
    }

    private static class ViewHolderPattern {
        public TextView name;
        public TextView desc;

        public ViewHolderPattern(View view) {
            this.name = (TextView) view.findViewById(R.id.text_view_name);
            this.desc = (TextView) view.findViewById(R.id.text_view_desc);
        }
    }
}
