package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Bidaia;
import modelo.pojo.Zerbitzu;

public class BidaiaTest {

    private Bidaia bidaia; // Probar Bidaia-ren instantzia
    private Bidaia bidaia2; // Probar Bidaia-ren bigarren instantzia
    private Bidaia bidaia3; // Probar Bidaia-ren hirugarren instantzia
    private ArrayList<Zerbitzu> zerbitzuak; // Probak egiteko Zerbitzuen zerrenda
    private Date bidaiHasiera; // Bidaiaren hasiera data
    private Date bidaiAmaiera; // Bidaiaren amaiera data

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() {
        // Bidaiaren hasiera eta amaiera datak inicializatu
        bidaiHasiera = Date.valueOf("2025-01-01");
        bidaiAmaiera = Date.valueOf("2025-01-10");

        // Zerbitzuen zerrenda inicializatu
        zerbitzuak = new ArrayList<>();
        // Zerbitzu objektuen zerrenda sortu

        zerbitzuak.add(new Zerbitzu(1, "Ostatu Zerbitzua", 1, 100, Date.valueOf("2025-02-13"),
                Date.valueOf("2025-02-20"), "Bilbo", "L1"));
        zerbitzuak.add(new Zerbitzu(2, "Hegaldi Zerbitzua", 2, "H1", Date.valueOf("2025-02-13"),
                Time.valueOf("10:00:00"), Time.valueOf("02:00:00"), 200, "BIO", "MAD", "A1"));

        // Bidaia instantziak sortu
        bidaia = new Bidaia(1, "Bidaia", "Bidaia deskribapena", "Ez Barne", bidaiHasiera, bidaiAmaiera, "ES", "B1", 100);
        bidaia2 = new Bidaia(zerbitzuak, 1, "Bidaia", "Bidaia deskribapena", "Ez Barne", bidaiHasiera, bidaiAmaiera, "ES", "B1", 100);
        bidaia3 = new Bidaia();
    }

    // gehituZerbitzuak metodoa testatu
    @Test
    public void testGehituZerbitzuak() {
        // Zerbitzu berri bat sortu eta Bidaia-ren zerbitzu zerrendara gehitu
        Zerbitzu nuevoZerbitzu = new Zerbitzu();
        bidaia.gehituZerbitzuak(nuevoZerbitzu);

        // Zerbitzua gehitu dela egiaztatu
        assertTrue(bidaia.getZerbitzuak().contains(nuevoZerbitzu));
        assertEquals(1, bidaia.getZerbitzuak().size());
    }

    // setZerbitzuak metodoa testatu
    @Test
    public void testSetZerbitzuak() {
        // Zerbitzu objektuen zerrenda Bidaia instantziari esleitu
        bidaia.setZerbitzuak(zerbitzuak);

        // Zerbitzuak behar bezala esleitu direla egiaztatu
        assertEquals(zerbitzuak, bidaia.getZerbitzuak());
    }

    // bidaiKodea eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetBidaiKodea() {
        // bidaiKodea ezarri "2"
        bidaia.setBidaiKodea(2);
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals(2, bidaia.getBidaiKodea());
    }

    // izena eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetIzena() {
        // izena ezarri "Bidaia Berria"
        bidaia.setIzena("Bidaia Berria");
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals("Bidaia Berria", bidaia.getIzena());
    }

    // deskribapena eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetDeskribapena() {
        // deskribapena ezarri "Deskribapen berria"
        bidaia.setDeskribapena("Deskribapen berria");
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals("Deskribapen berria", bidaia.getDeskribapena());
    }

    // EzBarne eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetEzBarne() {
        // EzBarne ezarri "Ez dago barne"
        bidaia.setEzBarne("Ez dago barne");
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals("Ez dago barne", bidaia.getEzBarne());
    }

    // bidaiHasiera eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetBidaiHasiera() {
        // bidaiHasiera ezarri data berriarekin "2025-02-01"
        Date newDate = Date.valueOf("2025-02-01");
        bidaia.setBidaiHasiera(newDate);
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals(newDate, bidaia.getBidaiHasiera());
    }

    // bidaiAmaiera eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetBidaiAmaiera() {
        // bidaiAmaiera ezarri data berriarekin "2025-02-10"
        Date newDate = Date.valueOf("2025-02-10");
        bidaia.setBidaiAmaiera(newDate);
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals(newDate, bidaia.getBidaiAmaiera());
    }

    // herrialdeKod eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetHerrialdeKod() {
        // herrialdeKod ezarri "FR"
        bidaia.setHerrialdeKod("FR");
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals("FR", bidaia.getHerrialdeKod());
    }

    // bidaiaMKod eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetBidaiaMKod() {
        // bidaiaMKod ezarri "B2"
        bidaia.setBidaiaMKod("B2");
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals("B2", bidaia.getBidaiaMKod());
    }

    // agentziaKod eskuratzea eta ezartzea testatu
    @Test
    public void testGetSetAgentziaKod() {
        // agentziaKod ezarri "200"
        bidaia.setAgentziaKod(200);
        // Ezarritako balioa espero den balioarekin alderatu
        assertEquals(200, bidaia.getAgentziaKod());
    }

    // toString metodoa testatu
    @Test
    public void testToString() {
        // Espero den stringa sortu
        String expected = "Bidaia [zerbitzuak=" + zerbitzuak + ", bidaiKodea=1, izena=Bidaia, deskribapena=Bidaia deskribapena, EzBarne=Ez Barne, bidaiHasiera=2025-01-01, bidaiAmaiera=2025-01-10, herrialdeKod=ES, bidaiaMKod=B1, agentziaKod=100]";
        // toString metodoaren irteera espero den stringarekin alderatu
        assertEquals(expected, bidaia.toString());
    }

    // toStringTxt metodoa testatu
    @Test
    public void testToStringTxt() {
        // Bidaia instantzia eta bere zerbitzuak konfiguratzen
        Date bidaiHasiera = Date.valueOf("2025-02-13");
        Date bidaiAmaiera = Date.valueOf("2025-02-20");
        Bidaia bidaia = new Bidaia(1, "Bidaia", "Bidaia deskribapena", "Ez Barne", bidaiHasiera, bidaiAmaiera, "ES", "B1", 100);

        // setUp-en inicializatutako zerbitzu zerrenda erabili
        bidaia.setZerbitzuak(zerbitzuak);

        // Espero den stringa sortu
        String expected = "Aurrekontua\n\n" +
                "Deskribapena = Bidaia deskribapena\n" +
                "EzBarne = Ez Barne\n" +
                "Bidaiaren Hasiera = 2025-02-13\n" +
                "Bidaiaren Amaiera = 2025-02-20\n" +
                "Herrialde Kodea = ES\n" +
                "Bidai Motaren Kodea = B1\n" +
                "Agentzia Kodea = 100\n\n" +
                "Zerbitzuaren izena = Ostatu Zerbitzua\n" +
                "Ostatu Prezioa = 100.0\n" +
                "Ostatu sarrera eguna = 2025-02-13\n" +
                "Ostatu irteera eguna = 2025-02-20\n" +
                "Hiria = Bilbo\n" +
                "Logela mota = L1\n\n" +
                "Zerbitzuaren izena = Hegaldi Zerbitzua\n" +
                "Hegaldi kodea = H1\n" +
                "Hegaldi irteera data = 2025-02-13\n" +
                "Hegaldi irteera ordua = 10:00:00\n" +
                "Hegaldi Bidaiaren iraupena = 02:00:00\n" +
                "Hegaldi prezioa = 200.0\n" +
                "Hegaldi jatorrizko aireportua = BIO\n" +
                "Hegaldi helmugako aireportua = MAD\n" +
                "Airelinea Kodea = A1\n";


     // ToStringTxt metodoaren irteera esperotakoa dela egiaztatzea

        assertEquals(expected, bidaia.toStringTxt());
    }
}