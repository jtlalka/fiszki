package net.tlalka.android.fiszki.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.model.types.LanguageType;

import java.util.List;

public class LanguageAdapter extends AbstractAdapter<LanguageType> {

    public LanguageAdapter(Context context, List<LanguageType> languageTypes) {
        super(context, languageTypes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = super.getLayoutInflater().inflate(R.layout.settings_spinner_item, viewGroup, false);
            convertView.setTag(new ViewHolderPattern(convertView));
        }

        ViewHolderPattern viewHolderPattern = (ViewHolderPattern) convertView.getTag();
        viewHolderPattern.item.setText(super.getItem(position).name());
        return convertView;
    }

    private static class ViewHolderPattern {
        public final TextView item;

        public ViewHolderPattern(View view) {
            this.item = (TextView) view.findViewById(R.id.spinner_item);
        }
    }
}
