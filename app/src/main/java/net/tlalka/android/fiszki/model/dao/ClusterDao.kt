package net.tlalka.android.fiszki.model.dao

import com.j256.ormlite.support.ConnectionSource
import net.tlalka.android.fiszki.model.entities.Cluster
import java.sql.SQLException

class ClusterDao @Throws(SQLException::class)
constructor(connectionSource: ConnectionSource) : AbstractDao<Cluster, Long>(connectionSource, Cluster::class.java)
