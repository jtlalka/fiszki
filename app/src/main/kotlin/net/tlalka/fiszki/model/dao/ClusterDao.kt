package net.tlalka.fiszki.model.dao

import com.j256.ormlite.support.ConnectionSource
import net.tlalka.fiszki.model.entities.Cluster
import java.sql.SQLException

class ClusterDao @Throws(SQLException::class)
constructor(connectionSource: ConnectionSource) : AbstractDao<Cluster, Long>(connectionSource, Cluster::class.java)
