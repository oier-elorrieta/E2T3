package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Logela_motak;

public class Logela_MotakTest {

    private Logela_motak logela_motak;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() throws Exception {
        // Logela_motak objektu bat sortu
        logela_motak = new Logela_motak("L1", "banakakoa");
    }

    // LogelaKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetLogelaKodea() {
        // LogelaKodea ezarri "L2"
        logela_motak.setLogelaKodea("L2");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("L2", logela_motak.getLogelaKodea());
    }

    // LogelaDeskribapena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetLogelaDeskribapena() {
        // LogelaDeskribapena ezarri "Bikoitza"
        logela_motak.setLogelaDeskribapena("Bikoitza");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("Bikoitza", logela_motak.getLogelaDeskribapena());
    }
}
