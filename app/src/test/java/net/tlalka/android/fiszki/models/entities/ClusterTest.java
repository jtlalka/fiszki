package net.tlalka.android.fiszki.models.entities;

import net.tlalka.android.fiszki.models.types.OwnerType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ClusterTest {

    @Test
    public void testCheekDefaultConstructor() {

        // when
        Cluster cluster = new Cluster();

        // then
        assertNotNull(cluster);
        assertNull(cluster.getOwnerType());
        assertNull(cluster.getWords());
    }

    @Test
    public void testCheekCustomConstructor() {

        // given
        OwnerType user = OwnerType.USER;

        // when
        Cluster cluster = new Cluster(user);

        // then
        assertNotNull(cluster);
        assertNotNull(cluster.getOwnerType());
        assertEquals(user, cluster.getOwnerType());
    }
}
