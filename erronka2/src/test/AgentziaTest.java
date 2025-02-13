package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Agentzia;

public class AgentziaTest {

    private Agentzia agentzia;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Agentzia objektu bat sortu
        agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
    }

    // Agentzia konstruktorerako test hutsa
    @Test
    public void testAgentzia() {
        Agentzia agentzia = new Agentzia();
    }

    // AgentziaKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAgentziaKodea() {
        // AgentziaKodea ezarri "2"
        agentzia.setAgentziaKodea(2);
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals(2, agentzia.getAgentziaKodea());
    }

    // LangileKopKod eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetLangileKopKod() {
        // LangileKopKod ezarri "L10"
        agentzia.setLangileKopKod("L10");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("L10", agentzia.getLangileKopKod());
    }

    // Logoa eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetLogoa() {
        // Logoa ezarri "logo.png"
        agentzia.setLogoa("logo.png");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("logo.png", agentzia.getLogoa());
    }

    // MarkarenKolorea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetMarkarenKolorea() {
        // Markaren kolorea ezarri "gorria"
        agentzia.setMarkarenKolorea("gorria");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("gorria", agentzia.getMarkarenKolorea());
    }

    // Izena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetIzena() {
        // Izena ezarri "alexjav"
        agentzia.setIzena("alexjav");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("alexjav", agentzia.getIzena());
    }

    // AgentziaMKod eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAgentziaMKod() {
        // AgentziaMKod ezarri "A2"
        agentzia.setAgentziaMKod("A2");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("A2", agentzia.getAgentziaMKod());
    }

    // Pasahitza eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetPasahitza() {
        // Pasahitza ezarri "alexjav"
        agentzia.setPasahitza("alexjav");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("alexjav", agentzia.getPasahitza());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Agentzia [agentziaKodea=1, langileKopKod=L3, logoa=www.www.com, markarenKolorea=urdina, izena=admin, agentziaMKod=A1, pasahitza=admin]";
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals(expected, agentzia.toString());
    }
}
