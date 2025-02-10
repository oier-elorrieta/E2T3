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
    public void testGetSetLangKopKodea() {
        langKopurua.setLangKopKodea("L3");
        assertEquals("L3", langKopurua.getLangKopKodea());
    }

   
    @Test
    public void testGetSetDeskribapena() {
        langKopurua.setDeskribapena("20 gehienez (1 - 20 bitartean)");
        assertEquals("20 gehienez (1 - 20 bitartean)", langKopurua.getDeskribapena());
    }

    @Test
    public void testToString() {
        String expected = "Lang_kopurua [langKopKodea=L1, deskribapena=5 gehienez ( 1 - 5 bitartean)]";
        assertEquals(expected, langKopurua.toString());
    }
}
