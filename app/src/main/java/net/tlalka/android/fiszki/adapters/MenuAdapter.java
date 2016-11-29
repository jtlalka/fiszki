package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.elements.PageElement;
import net.tlalka.android.fiszki.listeners.MenuListener;

import java.util.List;

public class MenuAdapter extends AbstractAdapter<PageElement> {

    public MenuAdapter(Context context, List<PageElement> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.main_item, viewGroup, false);
        }

        ViewHolderPattern viewHolderPattern = new ViewHolderPattern();
        PageElement pageElement = super.getItem(position);
        convertView.setTag(viewHolderPattern);

        viewHolderPattern.button = (Button) convertView.findViewById(R.id.button);
        viewHolderPattern.button.setText(super.getResourceAsString(pageElement.getResourceId()));
        viewHolderPattern.button.setOnClickListener(new MenuListener(context, pageElement));

        return convertView;
    }

    private static class ViewHolderPattern {
        public Button button;
    }
}
