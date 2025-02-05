package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import modelo.Bidaia;
import modelo.Zerbitzu;

public class BidaiaTest {

    private Bidaia bidaia;
    private ArrayList<Zerbitzu> zerbitzuak;
    private Date bidaiHasiera;
    private Date bidaiAmaiera;

    @Before
    public void setUp() {
        zerbitzuak = new ArrayList<>();
        bidaiHasiera = Date.valueOf("2025-01-01");
        bidaiAmaiera = Date.valueOf("2025-01-10");
        bidaia = new Bidaia(zerbitzuak, 1, "Bidaia", "Bidaia deskribapena", "Ez Barne", bidaiHasiera, bidaiAmaiera, "ES", "BM001", 100);
    }

    @Test
    public void testBidaia() {
        Bidaia bidaia = new Bidaia();
        assertNotNull(bidaia);
    }

    @Test
    public void testGetZerbitzuak() {
        assertEquals(zerbitzuak, bidaia.getZerbitzuak());
    }

    @Test
    public void testSetZerbitzuak() {
        ArrayList<Zerbitzu> newZerbitzuak = new ArrayList<>();
        bidaia.setZerbitzuak(newZerbitzuak);
        assertEquals(newZerbitzuak, bidaia.getZerbitzuak());
    }

    @Test
    public void testGetBidaiKodea() {
        assertEquals(1, bidaia.getBidaiKodea());
    }

    @Test
    public void testSetBidaiKodea() {
        bidaia.setBidaiKodea(2);
        assertEquals(2, bidaia.getBidaiKodea());
    }

    @Test
    public void testGetIzena() {
        assertEquals("Bidaia", bidaia.getIzena());
    }

    @Test
    public void testSetIzena() {
        bidaia.setIzena("Bidaia Berria");
        assertEquals("Bidaia Berria", bidaia.getIzena());
    }

    @Test
    public void testGetDeskribapena() {
        assertEquals("Bidaia deskribapena", bidaia.getDeskribapena());
    }

    @Test
    public void testSetDeskribapena() {
        bidaia.setDeskribapena("Deskribapen berria");
        assertEquals("Deskribapen berria", bidaia.getDeskribapena());
    }

    @Test
    public void testGetEzBarne() {
        assertEquals("Ez Barne", bidaia.getEzBarne());
    }

    @Test
    public void testSetEzBarne() {
        bidaia.setEzBarne("Ez dago barne");
        assertEquals("Ez dago barne", bidaia.getEzBarne());
    }

    @Test
    public void testGetBidaiHasiera() {
        assertEquals(bidaiHasiera, bidaia.getBidaiHasiera());
    }

    @Test
    public void testSetBidaiHasiera() {
        Date newDate = Date.valueOf("2025-02-01");
        bidaia.setBidaiHasiera(newDate);
        assertEquals(newDate, bidaia.getBidaiHasiera());
    }

    @Test
    public void testGetBidaiAmaiera() {
        assertEquals(bidaiAmaiera, bidaia.getBidaiAmaiera());
    }

    @Test
    public void testSetBidaiAmaiera() {
        Date newDate = Date.valueOf("2025-02-10");
        bidaia.setBidaiAmaiera(newDate);
        assertEquals(newDate, bidaia.getBidaiAmaiera());
    }

    @Test
    public void testGetHerrialdeKod() {
        assertEquals("ES", bidaia.getHerrialdeKod());
    }

    @Test
    public void testSetHerrialdeKod() {
        bidaia.setHerrialdeKod("FR");
        assertEquals("FR", bidaia.getHerrialdeKod());
    }

    @Test
    public void testGetBidaiaMKod() {
        assertEquals("BM001", bidaia.getBidaiaMKod());
    }

    @Test
    public void testSetBidaiaMKod() {
        bidaia.setBidaiaMKod("BM002");
        assertEquals("BM002", bidaia.getBidaiaMKod());
    }

    @Test
    public void testGetAgentziaKod() {
        assertEquals(100, bidaia.getAgentziaKod());
    }

    @Test
    public void testSetAgentziaKod() {
        bidaia.setAgentziaKod(200);
        assertEquals(200, bidaia.getAgentziaKod());
    }

    @Test
    public void testToString() {
        String expected = "Bidaia [zerbitzuak=" + zerbitzuak + ", bidaiKodea=1, izena=Bidaia, deskribapena=Bidaia deskribapena, EzBarne=Ez Barne, bidaiHasiera=" + bidaiHasiera + ", bidaiAmaiera=" + bidaiAmaiera + ", herrialdeKod=ES, bidaiaMKod=BM001, agentziaKod=100]";
        assertEquals(expected, bidaia.toString());
    }
}
