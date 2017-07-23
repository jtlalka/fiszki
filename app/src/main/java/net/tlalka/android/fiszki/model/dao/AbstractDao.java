package net.tlalka.android.fiszki.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import android.util.Log;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public abstract class AbstractDao<T, ID> extends BaseDaoImpl<T, ID> {

    protected AbstractDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public int create(T data) {
        try {
            return super.create(data);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "DB create exception", ex);
            return 0;
        }
    }

    @Override
    public int create(Collection<T> data) {
        try {
            return super.create(data);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "DB create exception", ex);
            return 0;
        }
    }

    @Override
    public int update(T data) {
        try {
            return super.update(data);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "DB update exception", ex);
            return 0;
        }
    }

    @Override
    public int delete(T data) {
        try {
            return super.delete(data);
        } catch (SQLException ex) {
            Log.e(this.getClass().getName(), "DB delete exception", ex);
            return 0;
        }
    }
}
