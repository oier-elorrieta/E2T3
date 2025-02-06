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
        bidaia = new Bidaia( 1, "Bidaia", "Bidaia deskribapena", "Ez Barne", bidaiHasiera, bidaiAmaiera, "ES", "B1", 100);
    }

   

  

    @Test
    public void testGetSetBidaiKodea() {
    	 bidaia.setBidaiKodea(2);
         assertEquals(2, bidaia.getBidaiKodea());
    }


    @Test
    public void testGetSetIzena() {
        bidaia.setIzena("Bidaia Berria");
        assertEquals("Bidaia Berria", bidaia.getIzena());
    }

   

    @Test
    public void testGetSetDeskribapena() {
        bidaia.setDeskribapena("Deskribapen berria");
        assertEquals("Deskribapen berria", bidaia.getDeskribapena());
    }

   

    @Test
    public void testGetSetEzBarne() {
        bidaia.setEzBarne("Ez dago barne");
        assertEquals("Ez dago barne", bidaia.getEzBarne());
    }

   
    @Test
    public void testGetSetBidaiHasiera() {
        Date newDate = Date.valueOf("2025-02-01");
        bidaia.setBidaiHasiera(newDate);
        assertEquals(newDate, bidaia.getBidaiHasiera());
    }

  
    @Test
    public void testGetSetBidaiAmaiera() {
        Date newDate = Date.valueOf("2025-02-10");
        bidaia.setBidaiAmaiera(newDate);
        assertEquals(newDate, bidaia.getBidaiAmaiera());
    }

   

    @Test
    public void testGetSetHerrialdeKod() {
        bidaia.setHerrialdeKod("FR");
        assertEquals("FR", bidaia.getHerrialdeKod());
    }

    
    @Test
    public void testGetSetBidaiaMKod() {
        bidaia.setBidaiaMKod("B2");
        assertEquals("B2", bidaia.getBidaiaMKod());
    }

   
    @Test
    public void testGetSetAgentziaKod() {
        bidaia.setAgentziaKod(200);
        assertEquals(200, bidaia.getAgentziaKod());
    }

    @Test
    public void testToString() {
        String expected = "Bidaia [bidaiKodea=1, izena=Bidaia, deskribapena=Bidaia deskribapena, EzBarne=Ez Barne, bidaiHasiera=" + bidaiHasiera + ", bidaiAmaiera=" + bidaiAmaiera + ", herrialdeKod=ES, bidaiaMKod=B1, agentziaKod=100]";
        assertEquals(expected, bidaia.toString());
    }
}
