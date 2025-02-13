package test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Zerbitzu;

public class ZerbitzuTest {

    private Zerbitzu ostatuZerbitzu;
    private Zerbitzu hegaldiZerbitzu;
    private Zerbitzu besteBatzukZerbitzu;
    private Zerbitzu joanEtorriZerbitzu;
    private Zerbitzu zerbitzu;
    private Date ostatuSarreraEguna;
    private Date ostatuIrteraEguna;
    private Date hegaldiIrteraData;
    private Time hegaldiIrteeraOrdutegia;
    private Time hegaldiBidaiarenIraupena;
    private Date egun;
    private Time itzuleraOrdua;
    private Date etorriaEguna;
    private Time bueltakoIraupena;

    // Test setup metodoa
    @Before
    public void setUp() {
        ostatuSarreraEguna = Date.valueOf("2025-01-01");
        ostatuIrteraEguna = Date.valueOf("2025-01-10");
        hegaldiIrteraData = Date.valueOf("2025-01-01");
        hegaldiIrteeraOrdutegia = Time.valueOf("10:00:00");
        hegaldiBidaiarenIraupena = Time.valueOf("02:00:00");
        egun = Date.valueOf("2025-01-15");
        itzuleraOrdua = Time.valueOf("12:00:00");
        etorriaEguna = Date.valueOf("2025-01-02");
        bueltakoIraupena = Time.valueOf("02:00:00");

        // Zerbitzuen hasierapena
        ostatuZerbitzu = new Zerbitzu(1, "Ostatu Zerbitzua", 1, 100, ostatuSarreraEguna, ostatuIrteraEguna, "Bilbo", "L1");
        hegaldiZerbitzu = new Zerbitzu(2, "Hegaldi Zerbitzua", 2, "H1", hegaldiIrteraData, hegaldiIrteeraOrdutegia, hegaldiBidaiarenIraupena, 200, "BIO", "MAD", "A1");
        besteBatzukZerbitzu = new Zerbitzu(3, "Beste Zerbitzua", 3, egun, "Deskribapena", 50);

        joanEtorriZerbitzu = new Zerbitzu(4, "Joan Etorri Zerbitzua", 4, "HE123", hegaldiIrteraData, hegaldiIrteeraOrdutegia, 
                                          hegaldiBidaiarenIraupena, 150.0f, "JFK", "LAX", "A123", "HE124", 
                                          itzuleraOrdua, etorriaEguna, bueltakoIraupena, "LAX", "JFK", "A124");
        zerbitzu = new Zerbitzu();
    }
    
    // Zerbitzu kodea testatzen duen metodoa
    @Test
    public void testGetSetZerbitzuKodea() {
        ostatuZerbitzu.setZerbitzuKodea(10);
        assertEquals(10, ostatuZerbitzu.getZerbitzuKodea());
    }

    // Zerbitzu izena testatzen duen metodoa
    @Test
    public void testGetSetZerbitzuIzena() {
        ostatuZerbitzu.setZerbitzuIzena("New Ostatu Zerbitzua");
        assertEquals("New Ostatu Zerbitzua", ostatuZerbitzu.getZerbitzuIzena());
    }

    // Bidai mota kodea testatzen duen metodoa
    @Test
    public void testGetSetBidaiMotaKodea() {
        ostatuZerbitzu.setBidaiKodea(4);
        assertEquals(4, ostatuZerbitzu.getBidaiKodea());
    }

    // Ostatu prezioa testatzen duen metodoa
    @Test
    public void testGetSetOstatuPrezioa() {
        ostatuZerbitzu.setOstatuPrezioa(150);
        assertEquals(150, ostatuZerbitzu.getOstatuPrezioa(), 0.01);
    }

    // Ostatu sarrera eguna testatzen duen metodoa
    @Test
    public void testGetSetOstatuSarreraEguna() {
        Date newDate = Date.valueOf("2025-02-01");
        ostatuZerbitzu.setOstatuSarreraEguna(newDate);
        assertEquals(newDate, ostatuZerbitzu.getOstatuSarreraEguna());
    }

    // Ostatu irtera eguna testatzen duen metodoa
    @Test
    public void testGetSetOstatuIrteraEguna() {
        Date newDate = Date.valueOf("2025-02-10");
        ostatuZerbitzu.setOstatuIrteraEguna(newDate);
        assertEquals(newDate, ostatuZerbitzu.getOstatuIrteraEguna());
    }

    // Hiria testatzen duen metodoa
    @Test
    public void testGetSetHiria() {
        ostatuZerbitzu.setHiria("Donostia");
        assertEquals("Donostia", ostatuZerbitzu.getHiria());
    }

    // Logela mota kodea testatzen duen metodoa
    @Test
    public void testGetSetLogelaMKodea() {
        ostatuZerbitzu.setLogelaMKodea("L2");
        assertEquals("L2", ostatuZerbitzu.getLogelaMKodea());
    }

    // Egun data testatzen duen metodoa
    @Test
    public void testGetSetEgun() {
        Date newDate = Date.valueOf("2025-01-20");
        besteBatzukZerbitzu.setEgun(newDate);
        assertEquals(newDate, besteBatzukZerbitzu.getEgun());
    }

    // Beste zerbitzu deskribapena testatzen duen metodoa
    @Test
    public void testGetSetBesteBatzukDeskribapena() {
        besteBatzukZerbitzu.setBesteBatzukDeskribapena("Deskribapena2");
        assertEquals("Deskribapena2", besteBatzukZerbitzu.getBesteBatzukDeskribapena());
    }

    // Beste zerbitzu prezioa testatzen duen metodoa
    @Test
    public void testGetSetBesteBatzukPrezioa() {
        besteBatzukZerbitzu.setBesteBatzukPrezioa(75);
        assertEquals(75, besteBatzukZerbitzu.getBesteBatzukPrezioa(), 0.01);
    }

    // Hegaldi kodea testatzen duen metodoa
    @Test
    public void testGetSetHegaldiKodea() {
        hegaldiZerbitzu.setHegaldiKodea("H2");
        assertEquals("H2", hegaldiZerbitzu.getHegaldiKodea());
    }

    // Hegaldi irtera data testatzen duen metodoa
    @Test
    public void testGetSetHegaldiIrteraData() {
        Date newDate = Date.valueOf("2025-02-01");
        hegaldiZerbitzu.setHegaldiIrteraData(newDate);
        assertEquals(newDate, hegaldiZerbitzu.getHegaldiIrteraData());
    }

    // Hegaldi irteera ordua testatzen duen metodoa
    @Test
    public void testGetSetHegaldiIrteeraOrdutegia() {
        Time newHegaldiIrteeraOrdutegia = Time.valueOf("22:00:00");
        hegaldiZerbitzu.setHegaldiIrteeraOrdutegia(newHegaldiIrteeraOrdutegia);
        assertEquals(newHegaldiIrteeraOrdutegia, hegaldiZerbitzu.getHegaldiIrteeraOrdutegia());
    }

    // Hegaldi bidaiaren iraupena testatzen duen metodoa
    @Test
    public void testGetSetHegaldiBidaiarenIraupena() {
        Time newHegaldiBidaiarenIraupena = Time.valueOf("03:00:00");
        hegaldiZerbitzu.setHegaldiBidaiarenIraupena(newHegaldiBidaiarenIraupena);
        assertEquals(newHegaldiBidaiarenIraupena, hegaldiZerbitzu.getHegaldiBidaiarenIraupena());
    }

    // Hegaldi prezioa testatzen duen metodoa
    @Test
    public void testGetSetHegaldiPrezioa() {
        hegaldiZerbitzu.setHegaldiPrezioa(22);
        assertEquals(22, hegaldiZerbitzu.getHegaldiPrezioa(), 0.01);
    }

    // Hegaldi jatorrizko aireportua testatzen duen metodoa
    @Test
    public void testGetSetHegaldiJatorrizkoAireportua() {
        hegaldiZerbitzu.setHegaldiJatorrizkoAireportua("Malaga");
        assertEquals("Malaga", hegaldiZerbitzu.getHegaldiJatorrizkoAireportua());
    }

    // Hegaldi helmugako aireportua testatzen duen metodoa
    @Test
    public void testGetSetHegaldiHelmugakoAireportua() {
        hegaldiZerbitzu.setHegaldiHelmugakoAireportua("Malaga");
        assertEquals("Malaga", hegaldiZerbitzu.getHegaldiHelmugakoAireportua());
    }

    // Airelina kodea testatzen duen metodoa
    @Test
    public void testGetSetAirelinaKodea() {
        hegaldiZerbitzu.setAirelinaKodea("A2");
        assertEquals("A2", hegaldiZerbitzu.getAirelinaKodea());
    }

    // Itzulera ordua testatzen duen metodoa
    @Test
    public void testGetSetItzuleraOrdua() {
        Time newItzuleraOrdua = Time.valueOf("03:00:00");
        hegaldiZerbitzu.setItzuleraOrdua(newItzuleraOrdua);
        assertEquals(newItzuleraOrdua, hegaldiZerbitzu.getItzuleraOrdua());
    }

    // Etorria eguna testatzen duen metodoa
    @Test
    public void testGetSetEtorriaEguna() {
        Date newDate = Date.valueOf("2025-02-17");
        hegaldiZerbitzu.setEtorriaEguna(newDate);
        assertEquals(newDate, hegaldiZerbitzu.getEtorriaEguna());
    }

    // Bueltako iraupena testatzen duen metodoa
    @Test
    public void testGetSetBueltakoIraupena() {
        Time newBueltakoIraupena = Time.valueOf("03:00:00");
        hegaldiZerbitzu.setBueltakoIraupena(newBueltakoIraupena);
        assertEquals(newBueltakoIraupena, hegaldiZerbitzu.getBueltakoIraupena());
    }

    // Joan jatorrizko aireportua testatzen duen metodoa
    @Test
    public void testGetSetJoanJatorrizkoAireportua() {
        hegaldiZerbitzu.setJoanJatorrizkoAireportua("Santander");
        assertEquals("Santander", hegaldiZerbitzu.getJoanJatorrizkoAireportua());
    }

    // Joan helmugako aireportua testatzen duen metodoa
    @Test
    public void testGetSetJoanHelmugakoAireportua() {
        hegaldiZerbitzu.setJoanHelmugakoAireportua("Santander");
        assertEquals("Santander", hegaldiZerbitzu.getJoanHelmugakoAireportua());
    }

    // Bueltako airelinea kodea testatzen duen metodoa
    @Test
    public void testGetSetBueltakoAirelineaKodea() {
        hegaldiZerbitzu.setBueltakoAirelineaKodea("B2");
        assertEquals("B2", hegaldiZerbitzu.getBueltakoAirelineaKodea());
    }

    // Zerbitzuaren toString metodoa testatzen duen metodoa
    @Test
    public void testToStringTxt() {
        String expected = "\n" +
                "Zerbitzuaren izena = Ostatu Zerbitzua\n" +
                "Ostatu Prezioa = 100.0\n" +
                "Ostatu sarrera eguna = 2025-02-13\n" +
                "Ostatu irteera eguna = 2025-02-20\n" +
                "Hiria = Bilbo\n" +
                "Logela mota = L1\n" +
                "Ostatu eguna = null\n" +
                "Joan-Etorri Deskribapena = null\n" +
                "Joan-Etorri prezioa = 0.0\n" +
                "Hegaldi kodea = null\n" +
                "Hegaldi irteera data = null\n" +
                "Hegaldi irteera ordua = null\n" +
                "Hegaldi Bidaiaren iraupena = null\n" +
                "Hegaldi prezioa = 0.0\n" +
                "Hegaldi jatorrizko aireportua = null\n" +
                "Hegaldi helmugako aireportua = null\n" +
                "Airelinea Kodea = null\n" +
                "Etorri hegaldiaren kodea = null\n" +
                "Itzulera ordua = null\n" +
                "Etorri eguna = null\n" +
                "Bueltako iraupena = null\n" +
                "Joan jatorrizko aireportua = null\n" +
                "Joan helmugako aireportua = null\n" +
                "Bueltako airelinea = null\n";
                
        assertEquals(expected, ostatuZerbitzu.toStringTxt());
    }
}

