package net.tlalka.fiszki.model.entities

import net.tlalka.fiszki.model.entities.Cluster
import net.tlalka.fiszki.model.types.OwnerType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class ClusterTest {

    @Test
    fun testCheekDefaultConstructor() {

        // when
        val cluster = Cluster()

        // then
        assertNotNull(cluster)
        assertEquals(OwnerType.SYSTEM, cluster.ownerType)
        assertNull(cluster.words)
    }

    @Test
    fun testCheekCustomConstructor() {

        // given
        val user = OwnerType.USER

        // when
        val cluster = Cluster(user)

        // then
        assertNotNull(cluster)
        assertNotNull(cluster.ownerType)
        assertEquals(user, cluster.ownerType)
    }
}
