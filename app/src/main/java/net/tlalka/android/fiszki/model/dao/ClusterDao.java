package net.tlalka.android.fiszki.model.dao;

import com.j256.ormlite.support.ConnectionSource;
import net.tlalka.android.fiszki.model.entities.Cluster;

import java.sql.SQLException;

public class ClusterDao extends AbstractDao<Cluster, Long> {

    public ClusterDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Cluster.class);
    }
}
