package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Lang_kopurua;

public class Lang_kopuruaTest {

    private Lang_kopurua langKopurua;

    @Before
    public void setUp() {
        langKopurua = new Lang_kopurua("L1", "5 gehienez ( 1 - 5 bitartean)");
    }

    @Test
    public void testLang_kopurua() {
        Lang_kopurua langKopurua = new Lang_kopurua("L2", "10 gehienez (1 - 10 bitartean)");
        assertNotNull(langKopurua);
        assertEquals("L2", langKopurua.getLangKopKodea());
        assertEquals("10 gehienez (1 - 10 bitartean)", langKopurua.getDeskribapena());
    }

    @Test
    public void testGetLangKopKodea() {
        assertEquals("L1", langKopurua.getLangKopKodea());
    }

    @Test
    public void testSetLangKopKodea() {
        langKopurua.setLangKopKodea("L3");
        assertEquals("L3", langKopurua.getLangKopKodea());
    }

    @Test
    public void testGetDeskribapena() {
        assertEquals("5 gehienez ( 1 - 5 bitartean)", langKopurua.getDeskribapena());
    }

    @Test
    public void testSetDeskribapena() {
        langKopurua.setDeskribapena("20 gehienez (1 - 20 bitartean)");
        assertEquals("20 gehienez (1 - 20 bitartean)", langKopurua.getDeskribapena());
    }

    @Test
    public void testToString() {
        String expected = "Lang_kopurua [langKopKodea=L1, deskribapena=5 gehienez ( 1 - 5 bitartean)]";
        assertEquals(expected, langKopurua.toString());
    }
}
