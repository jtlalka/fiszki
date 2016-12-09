package net.tlalka.android.fiszki.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

    protected final Context context;
    protected final List<T> elements;

    public AbstractAdapter(Context context, List<T> elements) {
        this.context = context;
        this.elements = elements;
    }

    public Resources getResources() {
        return this.context.getResources();
    }

    public String getString(int resId) {
        return this.getResources().getString(resId);
    }

    public String getString(int resId, Object... formatArgs) {
        return this.getResources().getString(resId, formatArgs);
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
