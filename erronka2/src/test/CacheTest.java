package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Agentzia;
import modelo.pojo.Cache;

public class CacheTest {

    private Agentzia agentzia; // Probar Agentzia-ren instantzia
    private Cache cache; // Probar Cache-ren instantzia

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Agentzia objektu bat sortu
        agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
        // Cache objektu bat sortu
        cache = new Cache();
    }

    // Agentzia eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAgentzia() {
        // Agentzia berri bat sortu
        Agentzia agentziaBerria = new Agentzia(2, "L8", "www.eee.com", "gorria", "alexjav", "A2", "alexjav");
        // Agentzia berria cache-ra esleitu
        cache.setAgentzia(agentziaBerria);
        // Konparatu ezarritako agentzia espero den agentziarekin
        assertEquals(agentziaBerria, cache.getAgentzia());
    }
}
