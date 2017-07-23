package net.tlalka.android.fiszki.view.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.entities.Lesson;

public class TestsAdapter extends AbstractAdapter<Lesson> {

    public TestsAdapter(Context context, List<Lesson> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = super.getLayoutInflater().inflate(R.layout.test_list_item, viewGroup, false);
            convertView.setTag(new ViewHolderPattern(convertView));
        }

        Lesson lesson = super.getItem(position);

        ViewHolderPattern viewHolderPattern = (ViewHolderPattern) convertView.getTag();
        viewHolderPattern.score.setText(getString(R.string.test_score_value, lesson.getScore()));
        viewHolderPattern.name.setText(getString(R.string.list_item, position + 1, lesson.getName()));
        viewHolderPattern.desc.setText(lesson.getLevelType().name());

        return convertView;
    }

    private static class ViewHolderPattern {
        public ImageView icon;
        public TextView score;
        public TextView name;
        public TextView desc;

        public ViewHolderPattern(View view) {
            this.icon = (ImageView) view.findViewById(R.id.test_list_icon);
            this.score = (TextView) view.findViewById(R.id.test_list_score);
            this.name = (TextView) view.findViewById(R.id.test_list_name);
            this.desc = (TextView) view.findViewById(R.id.test_list_desc);
        }
    }
}
