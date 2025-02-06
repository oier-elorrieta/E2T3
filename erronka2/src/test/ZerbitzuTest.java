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
		ostatuZerbitzu.setBidaiMotaKodea(4);
		assertEquals(4, ostatuZerbitzu.getBidaiMotaKodea());
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
		Time 
	}

	

	@Test
	public void testGetSetHegaldiBidaiarenIraupena() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetHegaldiPrezioa() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetHegaldiJatorrizkoAireportua() {
		fail("Not yet implemented");
	}



	@Test
	public void testGetSetHegaldiHelmugakoAireportua() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetAirelinaKodea() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetHegaldiKodeaEtorri() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetItzuleraOrdua() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetEtorriaEguna() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetBueltakoIraupena() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetJoanJatorrizkoAireportua() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetJoanHelmugakoAireportua() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetSetBueltakoAirelineaKodea() {
		fail("Not yet implemented");
	}
}
