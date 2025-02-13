package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Agentzia_Motak;

public class Agentzia_MotakTest {

    private Agentzia_Motak agentziaMotak;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Agentzia_Motak objektu bat sortu
        agentziaMotak = new Agentzia_Motak("A1", "Mayorista");
    }

    // AgentziaMKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAgentziaMKodea() {
        // AgentziaMKodea ezarri "A2"
        agentziaMotak.setAgentziaMKodea("A2");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("A2", agentziaMotak.getAgentziaMKodea());
    }

    // Deskribapena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetsetDeskribapena() {
        // Deskribapena ezarri "Minorista"
        agentziaMotak.setDeskribapena("Minorista");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("Minorista", agentziaMotak.getDeskribapena());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Agentzia_Motak [agentziaMKodea=A1, deskribapena=Mayorista]";
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals(expected, agentziaMotak.toString());
    }
}
