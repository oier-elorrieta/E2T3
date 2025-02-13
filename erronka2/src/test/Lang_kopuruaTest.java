package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Lang_kopurua;

public class Lang_kopuruaTest {

    private Lang_kopurua langKopurua;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Lang_kopurua objektu bat sortu
        langKopurua = new Lang_kopurua("L1", "5 gehienez ( 1 - 5 bitartean)");
    }

    // LangKopKodea eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetLangKopKodea() {
        // LangKopKodea ezarri "L3"
        langKopurua.setLangKopKodea("L3");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("L3", langKopurua.getLangKopKodea());
    }

    // Deskribapena eskuratzea eta ezartzea testatzen du
    @Test
    public void testGetSetDeskribapena() {
        // Deskribapena ezarri "20 gehienez (1 - 20 bitartean)"
        langKopurua.setDeskribapena("20 gehienez (1 - 20 bitartean)");
        // Konparatu ezarritako balioa espero den balioarekin
        assertEquals("20 gehienez (1 - 20 bitartean)", langKopurua.getDeskribapena());
    }

    // toString metodoa testatzen du
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Lang_kopurua [langKopKodea=L1, deskribapena=5 gehienez ( 1 - 5 bitartean)]";
        // Konparatu toString metodoaren irteera espero den stringarekin
        assertEquals(expected, langKopurua.toString());
    }
}
