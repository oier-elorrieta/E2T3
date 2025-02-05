package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Agentzia;

public class AgentziaTest {

    private Agentzia agentzia;

    @Before
    public void setUp() {
        agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
    }

    @Test
    public void testAgentziaIntStringStringStringStringStringString() {
        Agentzia agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
        assertNotNull(agentzia);
        assertEquals(1, agentzia.getAgentziaKodea());
        assertEquals("L3", agentzia.getLangileKopKod());
        assertEquals("www.www.com", agentzia.getLogoa());
        assertEquals("urdina", agentzia.getMarkarenKolorea());
        assertEquals("admin", agentzia.getIzena());
        assertEquals("A1", agentzia.getAgentziaMKod());
        assertEquals("admin", agentzia.getPasahitza());
    }

    @Test
    public void testAgentzia() {
        Agentzia agentzia = new Agentzia();
        assertNotNull(agentzia);
    }

    @Test
    public void testGetAgentziaKodea() {
        assertEquals(1, agentzia.getAgentziaKodea());
    }

    @Test
    public void testSetAgentziaKodea() {
        agentzia.setAgentziaKodea(2);
        assertEquals(2, agentzia.getAgentziaKodea());
    }

    @Test
    public void testGetLangileKopKod() {
        assertEquals("L3", agentzia.getLangileKopKod());
    }

    @Test
    public void testSetLangileKopKod() {
        agentzia.setLangileKopKod("L10");
        assertEquals("L10", agentzia.getLangileKopKod());
    }

    @Test
    public void testGetLogoa() {
        assertEquals("www.www.com", agentzia.getLogoa());
    }

    @Test
    public void testSetLogoa() {
        agentzia.setLogoa("logo.png");
        assertEquals("logo.png", agentzia.getLogoa());
    }

    @Test
    public void testGetMarkarenKolorea() {
        assertEquals("urdina", agentzia.getMarkarenKolorea());
    }

    @Test
    public void testSetMarkarenKolorea() {
        agentzia.setMarkarenKolorea("gorria");
        assertEquals("gorria", agentzia.getMarkarenKolorea());
    }

    @Test
    public void testGetIzena() {
        assertEquals("admin", agentzia.getIzena());
    }

    @Test
    public void testSetIzena() {
        agentzia.setIzena("alexjav");
        assertEquals("alexjav", agentzia.getIzena());
    }

    @Test
    public void testGetAgentziaMKod() {
        assertEquals("A1", agentzia.getAgentziaMKod());
    }

    @Test
    public void testSetAgentziaMKod() {
        agentzia.setAgentziaMKod("A2");
        assertEquals("A2", agentzia.getAgentziaMKod());
    }

    @Test
    public void testGetPasahitza() {
        assertEquals("admin", agentzia.getPasahitza());
    }

    @Test
    public void testSetPasahitza() {
        agentzia.setPasahitza("alexjav");
        assertEquals("alexjav", agentzia.getPasahitza());
    }

    @Test
    public void testToString() {
        String expected = "Agentzia [agentziaKodea=1, langileKopKod=L3, logoa=www.www.com, markarenKolorea=urdina, izena=admin, agentziaMKod=A1, pasahitza=admin]";
        assertEquals(expected, agentzia.toString());
    }
}
