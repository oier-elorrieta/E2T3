package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Bidai_Motak;

public class Bidaia_MotakTest {
    
    private Bidai_Motak bidai_motak;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() throws Exception {
        // Bidai_Motak objektu bat sortu
        bidai_motak = new Bidai_Motak("B1", "algo");
    }

    // BidaiKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetBidaiKodea() {
        // BidaiKodea ezarri "B2"
        bidai_motak.setBidaiKodea("B2");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("B2", bidai_motak.getBidaiKodea());
    }

    // Deskribapena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetDeskribapena() {
        // Deskribapena ezarri "otra cosa"
        bidai_motak.setDeskribapena("otra cosa");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("otra cosa", bidai_motak.getDeskribapena());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals("Herrialde [BidaiKodea=B1, Deskribapena=algo]", bidai_motak.toString());
    }
}
