package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Aireportu;

public class AireportuTest {

    private Aireportu aeroportu;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Aireportu objektu bat sortu
        aeroportu = new Aireportu("BIO", "Bilbao");
    }

    // AireportuKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetAireportuKodea() {
        // AireportuKodea ezarri "MAD"
        aeroportu.setAireportuKodea("MAD");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("MAD", aeroportu.getAireportuKodea());
    }

    // Hiria eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetHiria() {
        // Hiria ezarri "Madrid"
        aeroportu.setHiria("Madrid");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("Madrid", aeroportu.getHiria());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Aeroportu [aireportuKodea=BIO, hiria=Bilbao]";
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals(expected, aeroportu.toString());
    }
}
