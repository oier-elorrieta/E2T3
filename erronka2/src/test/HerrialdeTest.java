package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Herrialde;

public class HerrialdeTest {

    private Herrialde herrialde;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Herrialde objektu bat sortu
        herrialde = new Herrialde("ES", "España");
    }

    // HerrialdeKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetHerrialdeKodea() {
        // HerrialdeKodea ezarri "FR"
        herrialde.setHerrialdeKodea("FR");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("FR", herrialde.getHerrialdeKodea());
    }

    // Helmuga eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetHelmuga() {
        // Helmuga ezarri "Francia"
        herrialde.setHelmuga("Francia");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("Francia", herrialde.getHelmuga());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Herrialde [herrialdeKodea=ES, helmuga=España]";
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals(expected, herrialde.toString());
    }
}
