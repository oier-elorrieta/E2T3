package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import org.junit.Before;
import org.junit.Test;

import modelo.Zerbitzu;

public class ZerbitzuTest {

	private Zerbitzu ostatuZerbitzu;
	private Zerbitzu hegaldiZerbitzu;
	private Zerbitzu besteBatzukZerbitzu;
	private Date ostatuSarreraEguna;
	private Date ostatuIrteraEguna;
	private Date hegaldiIrteraData;
	private Time hegaldiIrteeraOrdutegia;
	private Time hegaldiBidaiarenIraupena;
	private Date egun;

	@Before
	public void setUp() {
		ostatuSarreraEguna = Date.valueOf("2025-01-01");
		ostatuIrteraEguna = Date.valueOf("2025-01-10");
		hegaldiIrteraData = Date.valueOf("2025-01-01");
		hegaldiIrteeraOrdutegia = Time.valueOf("10:00:00");
		hegaldiBidaiarenIraupena = Time.valueOf("02:00:00");
		egun = Date.valueOf("2025-01-15");

		ostatuZerbitzu = new Zerbitzu(1, "Ostatu Zerbitzua", 1, 100, ostatuSarreraEguna, ostatuIrteraEguna, "Bilbo",
				"Hotel Bilbo", "L1");
		hegaldiZerbitzu = new Zerbitzu(2, "Hegaldi Zerbitzua", 2, "H1", hegaldiIrteraData, hegaldiIrteeraOrdutegia,
				hegaldiBidaiarenIraupena, 200, "BIO", "MAD", "A1");
		besteBatzukZerbitzu = new Zerbitzu(3, "Beste Zerbitzua", 3, egun, "Deskribapena", 50);
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

	

	@Test
	public void testGetSetOstatuIzena() {
		ostatuZerbitzu.setOstatuIzena("Hotel Javi");
		assertEquals("Hotel Javi", ostatuZerbitzu.getOstatuIzena());
	}

	

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
}
