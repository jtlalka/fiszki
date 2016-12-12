package net.tlalka.android.fiszki.services;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageHelper {

    private final SharedPreferences sharedPreferences;

    public StorageHelper() {
        this.sharedPreferences = this.getSharedPreferences(AppHelper.getContext());
    }

    public StorageHelper(Context context) {
        this.sharedPreferences = this.getSharedPreferences(context);
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(this.getClass().getName(), Context.MODE_PRIVATE);
    }

    public boolean getBoolean(Enum key, boolean defValue) {
        return this.sharedPreferences.getBoolean(key.name(), defValue);
    }

    public void setBoolean(Enum key, boolean value) {
        this.sharedPreferences.edit().putBoolean(key.name(), value).apply();
    }

    public long getValue(Enum key, long defValue) {
        return this.sharedPreferences.getLong(key.name(), defValue);
    }

    public void setValue(Enum key, long value) {
        this.sharedPreferences.edit().putLong(key.name(), value).apply();
    }

    public <E extends Enum<E>> E getEnum(Enum key, Enum<E> defValue) {
        return E.valueOf(defValue.getDeclaringClass(), this.sharedPreferences.getString(key.name(), defValue.name()));
    }

    public <E extends Enum<E>> void setEnum(Enum key, E value) {
        this.sharedPreferences.edit().putString(key.name(), value.name()).apply();
    }
}
