package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Hiria;

public class HiriaTest {

    private Hiria hiria;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Hiria objektu bat sortu
        hiria = new Hiria(1, "Bilbo");
    }

    // HiriKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetHiriKode() {
        // HiriKodea ezarri "2"
        hiria.setHiriKode(2);
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals(2, hiria.getHiriKode());
    }

    // Izena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetIzena() {
        // Izena ezarri "Donostia"
        hiria.setIzena("Donostia");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("Donostia", hiria.getIzena());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Hiria [hiriKode=1, izena=Bilbo]";
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals(expected, hiria.toString());
    }
}
