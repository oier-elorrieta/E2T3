package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Agentzia;
import modelo.Cache;

public class CacheTest {

    private Agentzia agentzia;

    @Before
    public void setUp() {
        agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
        Cache.setAgentzia(agentzia);
    }

    @Test
    public void testCache() {
        Cache cache = new Cache();
        assertNotNull(cache);
    }

    @Test
    public void testGetAgentzia() {
        assertEquals(agentzia, Cache.getAgentzia());
    }

    @Test
    public void testSetAgentzia() {
        Agentzia nuevaAgentzia = new Agentzia(2, "L8", "www.eee.com", "gorria", "alexjav", "A2", "alexjav");
        Cache.setAgentzia(nuevaAgentzia);
        assertEquals(nuevaAgentzia, Cache.getAgentzia());
    }
}
