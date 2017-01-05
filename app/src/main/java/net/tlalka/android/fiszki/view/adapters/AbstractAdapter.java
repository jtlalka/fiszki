package net.tlalka.android.fiszki.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

    private final Context context;
    private final List<T> elements;

    public AbstractAdapter(Context context, List<T> elements) {
        this.context = context;
        this.elements = elements;
    }

    public Context getContext() {
        return this.context;
    }

    public String getString(int resId) {
        return this.context.getResources().getString(resId);
    }

    public String getString(int resId, Object... formatArgs) {
        return this.context.getResources().getString(resId, formatArgs);
    }

    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(this.context);
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
