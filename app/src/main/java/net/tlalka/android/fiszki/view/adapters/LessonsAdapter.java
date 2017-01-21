package net.tlalka.android.fiszki.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.entities.Lesson;

import java.util.List;

public class LessonsAdapter extends AbstractAdapter<Lesson> {

    public LessonsAdapter(Context context, List<Lesson> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = super.getLayoutInflater().inflate(R.layout.lesson_list_item, viewGroup, false);
            convertView.setTag(new ViewHolderPattern(convertView));
        }

        Lesson lesson = super.getItem(position);

        ViewHolderPattern viewHolderPattern = (ViewHolderPattern) convertView.getTag();
        viewHolderPattern.icon.setImageResource(getIconId(lesson.getProgress()));
        viewHolderPattern.name.setText(getString(R.string.list_item, position + 1, lesson.getName()));
        viewHolderPattern.desc.setText(lesson.getLevelType().name());

        return convertView;
    }

    private int getIconId(int progress) {
        return progress > 0 ? R.drawable.lessons_item_checked : R.drawable.lessons_item_empty;
    }

    private static class ViewHolderPattern {
        public ImageView icon;
        public TextView name;
        public TextView desc;

        public ViewHolderPattern(View view) {
            this.icon = (ImageView) view.findViewById(R.id.lesson_list_icon);
            this.name = (TextView) view.findViewById(R.id.lesson_list_name);
            this.desc = (TextView) view.findViewById(R.id.lesson_list_desc);
        }
    }
}
