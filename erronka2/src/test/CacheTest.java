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
        
    }

   

    

    @Test
    public void testGetSetAgentzia() {
        Agentzia nuevaAgentzia = new Agentzia(2, "L8", "www.eee.com", "gorria", "alexjav", "A2", "alexjav");
        Cache.setAgentzia(nuevaAgentzia);
        assertEquals(nuevaAgentzia, Cache.getAgentzia());
    }
}
