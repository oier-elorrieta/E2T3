package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Agentzia_Motak;

public class Agentzia_MotakTest {

    private Agentzia_Motak agentziaMotak;

    @Before
    public void setUp() {
        agentziaMotak = new Agentzia_Motak("A1", "Mayorista");
    }

    @Test
    public void testAgentzia_Motak() {
        assertNotNull(agentziaMotak);
    }

    @Test
    public void testGetAgentziaMKodea() {
        assertEquals("A1", agentziaMotak.getAgentziaMKodea());
    }

    @Test
    public void testSetAgentziaMKodea() {
        agentziaMotak.setAgentziaMKodea("A2");
        assertEquals("A2", agentziaMotak.getAgentziaMKodea());
    }

    @Test
    public void testGetDeskribapena() {
        assertEquals("Mayorista", agentziaMotak.getDeskribapena());
    }

    @Test
    public void testSetDeskribapena() {
        agentziaMotak.setDeskribapena("Minorista");
        assertEquals("Minorista", agentziaMotak.getDeskribapena());
    }

    @Test
    public void testToString() {
        String expected = "Agentzia_Motak [agentziaMKodea=A1, deskribapena=Mayorista]";
        assertEquals(expected, agentziaMotak.toString());
    }
}
