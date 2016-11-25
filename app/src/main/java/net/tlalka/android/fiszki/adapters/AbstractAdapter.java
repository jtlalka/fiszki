package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

    protected List<T> elements;
    protected Context context;

    public AbstractAdapter(Context context, List<T> elements) {
        this.elements = elements;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public T getItem(int position) {
        return this.elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup viewGroup);
}
