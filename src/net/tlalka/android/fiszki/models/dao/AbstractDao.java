package net.tlalka.android.fiszki.models.dao;

import java.sql.SQLException;

import android.util.Log;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

public abstract class AbstractDao<T, ID> extends BaseDaoImpl<T, ID> implements Dao<T, ID> {

	protected AbstractDao(Class<T> dataClass) throws SQLException {
		super(dataClass);
	}

	protected AbstractDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}
	
	public CreateOrUpdateStatus save(T dataDao) {
		try {
			return super.createOrUpdate(dataDao);
		} catch (SQLException ex) {
			Log.e(this.getClass().getName(), ex.getMessage());
			return new CreateOrUpdateStatus(false, false, 0);
		}
	}
}
