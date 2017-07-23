package net.tlalka.android.fiszki.view.adapters;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.view.elements.PageElement;
import net.tlalka.android.fiszki.view.listeners.MenuListener;

public class MenuAdapter extends AbstractAdapter<PageElement> {

    public MenuAdapter(Context context, List<PageElement> elements) {
        super(context, elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = super.getLayoutInflater().inflate(R.layout.main_list_item, viewGroup, false);
        }

        ViewHolderPattern viewHolderPattern = new ViewHolderPattern();
        PageElement pageElement = super.getItem(position);
        convertView.setTag(viewHolderPattern);

        viewHolderPattern.button = (Button) convertView.findViewById(R.id.button_menu_item);
        viewHolderPattern.button.setText(super.getString(pageElement.getResourceId()));
        viewHolderPattern.button.setOnClickListener(new MenuListener(super.getContext(), pageElement));

        return convertView;
    }

    private static class ViewHolderPattern {
        public Button button;
    }
}
