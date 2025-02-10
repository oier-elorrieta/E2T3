package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Agentzia;
import modelo.Cache;

public class CacheTest {

    private Agentzia agentzia;
    private Cache cache;
    @Before
    public void setUp() {
        agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
        cache = new Cache();
    }

   

    

    @Test
    public void testGetSetAgentzia() {
        Agentzia agentziaBerria = new Agentzia(2, "L8", "www.eee.com", "gorria", "alexjav", "A2", "alexjav");
        cache.setAgentzia(agentziaBerria);
        assertEquals(agentziaBerria, cache.getAgentzia());
    }
}
