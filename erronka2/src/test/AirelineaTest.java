package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Airelinea;

public class AirelineaTest {

    private Airelinea airelinea;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() throws Exception {
        // Airelinea objektu bat sortu
        airelinea = new Airelinea("A1", "Loiu", "H1");
    }

    // AirelineaKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAirelineaKodea() {
        // AirelineaKodea ezarri "A2"
        airelinea.setAirelineaKodea("A2");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("A2", airelinea.getAirelineaKodea());
    }

    // AirelineIzena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAirelineIzena() {
        // Aireline izena ezarri "Santander"
        airelinea.setAirelineIzena("Santander");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("Santander", airelinea.getAirelineIzena());
    }

    // HerrialdeKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetHerrialdeKodea() {
        // HerrialdeKodea ezarri "H2"
        airelinea.setHerrialdeKodea("H2");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("H2", airelinea.getHerrialdeKodea());
    }
}
