package test;

import static org.junit.Assert.*;

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

        ostatuZerbitzu = new Zerbitzu(1, "Ostatu Zerbitzua", 1, 100, ostatuSarreraEguna, ostatuIrteraEguna, "Bilbo", "L1");
        hegaldiZerbitzu = new Zerbitzu(2, "Hegaldi Zerbitzua", 2, "H1", hegaldiIrteraData, hegaldiIrteeraOrdutegia, hegaldiBidaiarenIraupena, 200, "BIO", "MAD", "A1");
        besteBatzukZerbitzu = new Zerbitzu(3, "Beste Zerbitzua", 3, egun, "Deskribapena", 50);

        joanEtorriZerbitzu = new Zerbitzu(4, "Joan Etorri Zerbitzua", 4, "HE123", hegaldiIrteraData, hegaldiIrteeraOrdutegia, 
                                          hegaldiBidaiarenIraupena, 150.0f, "JFK", "LAX", "A123", "HE124", 
                                          itzuleraOrdua, etorriaEguna, bueltakoIraupena, "LAX", "JFK", "A124");
        zerbitzu = new Zerbitzu();
	}

	

	
	
	@Test
	public void testGetSetZerbitzuKodea() {
		ostatuZerbitzu.setZerbitzuKodea(10);
		assertEquals(10, ostatuZerbitzu.getZerbitzuKodea());
	}

	

	@Test
	public void testGetSetZerbitzuIzena() {
		ostatuZerbitzu.setZerbitzuIzena("New Ostatu Zerbitzua");
		assertEquals("New Ostatu Zerbitzua", ostatuZerbitzu.getZerbitzuIzena());
	}


	@Test
	public void testGetSetBidaiMotaKodea() {
		ostatuZerbitzu.setBidaiKodea(4);
		assertEquals(4, ostatuZerbitzu.getBidaiKodea());
	}

	

	@Test
	public void testGetSetOstatuPrezioa() {
		ostatuZerbitzu.setOstatuPrezioa(150);
		assertEquals(150, ostatuZerbitzu.getOstatuPrezioa(), 0.01);
	}

	

	@Test
	public void testGetSetOstatuSarreraEguna() {
		Date newDate = Date.valueOf("2025-02-01");
		ostatuZerbitzu.setOstatuSarreraEguna(newDate);
		assertEquals(newDate, ostatuZerbitzu.getOstatuSarreraEguna());
	}

	

	@Test
	public void testGetSetOstatuIrteraEguna() {
		Date newDate = Date.valueOf("2025-02-10");
		ostatuZerbitzu.setOstatuIrteraEguna(newDate);
		assertEquals(newDate, ostatuZerbitzu.getOstatuIrteraEguna());
	}

	

	@Test
	public void testGetSetHiria() {
		ostatuZerbitzu.setHiria("Donostia");
		assertEquals("Donostia", ostatuZerbitzu.getHiria());
	}

	

	/*@Test
	public void testGetSetOstatuIzena() {
		ostatuZerbitzu.setOstatuIzena("Hotel Javi");
		assertEquals("Hotel Javi", ostatuZerbitzu.getOstatuIzena());
	}*/

	

	@Test
	public void testGetSetLogelaMKodea() {
		ostatuZerbitzu.setLogelaMKodea("L2");
		assertEquals("L2", ostatuZerbitzu.getLogelaMKodea());
	}

	

	@Test
	public void testGetSetEgun() {
		Date newDate = Date.valueOf("2025-01-20");
		besteBatzukZerbitzu.setEgun(newDate);
		assertEquals(newDate, besteBatzukZerbitzu.getEgun());
	}

	

	@Test
	public void testGetSetBesteBatzukDeskribapena() {
		besteBatzukZerbitzu.setBesteBatzukDeskribapena("Deskribapena2");
		assertEquals("Deskribapena2", besteBatzukZerbitzu.getBesteBatzukDeskribapena());
	}

	

	@Test
	public void testGetSetBesteBatzukPrezioa() {
		besteBatzukZerbitzu.setBesteBatzukPrezioa(75);
		assertEquals(75, besteBatzukZerbitzu.getBesteBatzukPrezioa(), 0.01);
	}

	

	@Test
	public void testGetSetHegaldiKodea() {
		hegaldiZerbitzu.setHegaldiKodea("H2");
		assertEquals("H2", hegaldiZerbitzu.getHegaldiKodea());
	}

	

	@Test
	public void testGetSetHegaldiIrteraData() {
		Date newDate = Date.valueOf("2025-02-01");
		hegaldiZerbitzu.setHegaldiIrteraData(newDate);
		assertEquals(newDate, hegaldiZerbitzu.getHegaldiIrteraData());
	}

	

	@Test
	public void testGetSetHegaldiIrteeraOrdutegia() {
		Time newHegaldiIrteeraOrdutegia = Time.valueOf("22:00:00");
		hegaldiZerbitzu.setHegaldiIrteeraOrdutegia(newHegaldiIrteeraOrdutegia);
		assertEquals(newHegaldiIrteeraOrdutegia,hegaldiZerbitzu.getHegaldiIrteeraOrdutegia());
	}

	

	@Test
	public void testGetSetHegaldiBidaiarenIraupena() {
		Time newHegaldiBidaiarenIraupena = Time.valueOf("03:00:00");
		hegaldiZerbitzu.setHegaldiBidaiarenIraupena(newHegaldiBidaiarenIraupena);
		assertEquals(newHegaldiBidaiarenIraupena,hegaldiZerbitzu.getHegaldiBidaiarenIraupena());
	
	}

	

	@Test
	public void testGetSetHegaldiPrezioa() {
		hegaldiZerbitzu.setHegaldiPrezioa(22);
		assertEquals(22,hegaldiZerbitzu.getHegaldiPrezioa(),0.01);
	}

	

	@Test
	public void testGetSetHegaldiJatorrizkoAireportua() {
		hegaldiZerbitzu.setHegaldiJatorrizkoAireportua("Malaga");
		assertEquals("Malaga",hegaldiZerbitzu.getHegaldiJatorrizkoAireportua());
	}



	@Test
	public void testGetSetHegaldiHelmugakoAireportua() {
		hegaldiZerbitzu.setHegaldiHelmugakoAireportua("Malaga");
		assertEquals("Malaga",hegaldiZerbitzu.getHegaldiHelmugakoAireportua());

	}

	

	@Test
	public void testGetSetAirelinaKodea() {
		hegaldiZerbitzu.setAirelinaKodea("A2");
		assertEquals("A2",hegaldiZerbitzu.getAirelinaKodea());
	}

	

	@Test
	public void testGetSetHegaldiKodeaEtorri() {
		hegaldiZerbitzu.setHegaldiKodeaEtorri("E2");
		assertEquals("E2",hegaldiZerbitzu.getHegaldiKodeaEtorri());

	}

	

	@Test
	public void testGetSetItzuleraOrdua() {
		Time newItzuleraOrdua = Time.valueOf("03:00:00");
		hegaldiZerbitzu.setItzuleraOrdua(newItzuleraOrdua);
		assertEquals(newItzuleraOrdua,hegaldiZerbitzu.getItzuleraOrdua());
	
	}

	

	@Test
	public void testGetSetEtorriaEguna() {
		Date newDate = Date.valueOf("2025-02-17");
		hegaldiZerbitzu.setEtorriaEguna(newDate);
		assertEquals(newDate, hegaldiZerbitzu.getEtorriaEguna());

	}

	

	@Test
	public void testGetSetBueltakoIraupena() {
		Time newBueltakoIraupena = Time.valueOf("03:00:00");
		hegaldiZerbitzu.setBueltakoIraupena(newBueltakoIraupena);
		assertEquals(newBueltakoIraupena,hegaldiZerbitzu.getBueltakoIraupena());
	
	}

	

	@Test
	public void testGetSetJoanJatorrizkoAireportua() {
		hegaldiZerbitzu.setJoanJatorrizkoAireportua("Santander");
		assertEquals("Santander",hegaldiZerbitzu.getJoanJatorrizkoAireportua());
	}

	

	@Test
	public void testGetSetJoanHelmugakoAireportua() {
		hegaldiZerbitzu.setJoanHelmugakoAireportua("Santander");
		assertEquals("Santander",hegaldiZerbitzu.getJoanHelmugakoAireportua());
	
	}

	

	@Test
	public void testGetSetBueltakoAirelineaKodea() {
		hegaldiZerbitzu.setBueltakoAirelineaKodea("B2");
		assertEquals("B2",hegaldiZerbitzu.getBueltakoAirelineaKodea());
	}
	
	@Test
    public void testToString() {
        String expected = "Zerbitzu [zerbitzuKodea=4, zerbitzuIzena=Joan Etorri Zerbitzua, bidaiKodea=4, hegaldiKodea=HE123, hegaldiIrteraData=2025-01-01, hegaldiIrteeraOrdutegia=10:00:00, hegaldiBidaiarenIraupena=02:00:00, hegaldiPrezioa=150.0, hegaldiJatorrizkoAireportua=JFK, hegaldiHelmugakoAireportua=LAX, airelinaKodea=A123, hegaldiKodeaEtorri=HE124, itzuleraOrdua=12:00:00, etorriaEguna=2025-01-02, bueltakoIraupena=02:00:00, joanJatorrizkoAireportua=LAX, joanHelmugakoAireportua=JFK, bueltakoAirelineaKodea=A124]";
        assertEquals(expected, joanEtorriZerbitzu.toString());
    }
}
