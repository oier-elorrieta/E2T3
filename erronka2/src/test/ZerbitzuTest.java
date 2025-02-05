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
	public void testGetZerbitzuKodea() {
		assertEquals(1, ostatuZerbitzu.getZerbitzuKodea());
	}

	@Test
	public void testSetZerbitzuKodea() {
		ostatuZerbitzu.setZerbitzuKodea(10);
		assertEquals(10, ostatuZerbitzu.getZerbitzuKodea());
	}

	@Test
	public void testGetZerbitzuIzena() {
		assertEquals("Ostatu Zerbitzua", ostatuZerbitzu.getZerbitzuIzena());
	}

	@Test
	public void testSetZerbitzuIzena() {
		ostatuZerbitzu.setZerbitzuIzena("New Ostatu Zerbitzua");
		assertEquals("New Ostatu Zerbitzua", ostatuZerbitzu.getZerbitzuIzena());
	}

	@Test
	public void testGetBidaiMotaKodea() {
		assertEquals(1, ostatuZerbitzu.getBidaiMotaKodea());
	}

	@Test
	public void testSetBidaiMotaKodea() {
		ostatuZerbitzu.setBidaiMotaKodea(4);
		assertEquals(4, ostatuZerbitzu.getBidaiMotaKodea());
	}

	@Test
	public void testGetOstatuPrezioa() {
		assertEquals(100, ostatuZerbitzu.getOstatuPrezioa(), 0.01);
	}

	@Test
	public void testSetOstatuPrezioa() {
		ostatuZerbitzu.setOstatuPrezioa(150);
		assertEquals(150, ostatuZerbitzu.getOstatuPrezioa(), 0.01);
	}

	@Test
	public void testGetOstatuSarreraEguna() {
		assertEquals(ostatuSarreraEguna, ostatuZerbitzu.getOstatuSarreraEguna());
	}

	@Test
	public void testSetOstatuSarreraEguna() {
		Date newDate = Date.valueOf("2025-02-01");
		ostatuZerbitzu.setOstatuSarreraEguna(newDate);
		assertEquals(newDate, ostatuZerbitzu.getOstatuSarreraEguna());
	}

	@Test
	public void testGetOstatuIrteraEguna() {
		assertEquals(ostatuIrteraEguna, ostatuZerbitzu.getOstatuIrteraEguna());
	}

	@Test
	public void testSetOstatuIrteraEguna() {
		Date newDate = Date.valueOf("2025-02-10");
		ostatuZerbitzu.setOstatuIrteraEguna(newDate);
		assertEquals(newDate, ostatuZerbitzu.getOstatuIrteraEguna());
	}

	@Test
	public void testGetHiria() {
		assertEquals("Bilbo", ostatuZerbitzu.getHiria());
	}

	@Test
	public void testSetHiria() {
		ostatuZerbitzu.setHiria("Donostia");
		assertEquals("Donostia", ostatuZerbitzu.getHiria());
	}

	@Test
	public void testGetOstatuIzena() {
		assertEquals("Hotel Bilbo", ostatuZerbitzu.getOstatuIzena());
	}

	@Test
	public void testSetOstatuIzena() {
		ostatuZerbitzu.setOstatuIzena("Hotel Javi");
		assertEquals("Hotel Javi", ostatuZerbitzu.getOstatuIzena());
	}

	@Test
	public void testGetLogelaMKodea() {
		assertEquals("L1", ostatuZerbitzu.getLogelaMKodea());
	}

	@Test
	public void testSetLogelaMKodea() {
		ostatuZerbitzu.setLogelaMKodea("L2");
		assertEquals("L2", ostatuZerbitzu.getLogelaMKodea());
	}

	@Test
	public void testGetEgun() {
		assertEquals(egun, besteBatzukZerbitzu.getEgun());
	}

	@Test
	public void testSetEgun() {
		Date newDate = Date.valueOf("2025-01-20");
		besteBatzukZerbitzu.setEgun(newDate);
		assertEquals(newDate, besteBatzukZerbitzu.getEgun());
	}

	@Test
	public void testGetBesteBatzukDeskribapena() {
		assertEquals("Deskribapena", besteBatzukZerbitzu.getBesteBatzukDeskribapena());
	}

	@Test
	public void testSetBesteBatzukDeskribapena() {
		besteBatzukZerbitzu.setBesteBatzukDeskribapena("Deskribapena2");
		assertEquals("Deskribapena2", besteBatzukZerbitzu.getBesteBatzukDeskribapena());
	}

	@Test
	public void testGetBesteBatzukPrezioa() {
		assertEquals(50, besteBatzukZerbitzu.getBesteBatzukPrezioa(), 0.01);
	}

	@Test
	public void testSetBesteBatzukPrezioa() {
		besteBatzukZerbitzu.setBesteBatzukPrezioa(75);
		assertEquals(75, besteBatzukZerbitzu.getBesteBatzukPrezioa(), 0.01);
	}

	@Test
	public void testGetHegaldiKodea() {
		assertEquals("H1", hegaldiZerbitzu.getHegaldiKodea());
	}

	@Test
	public void testSetHegaldiKodea() {
		hegaldiZerbitzu.setHegaldiKodea("H2");
		assertEquals("H2", hegaldiZerbitzu.getHegaldiKodea());
	}

	@Test
	public void testGetHegaldiIrteraData() {
		assertEquals(hegaldiIrteraData, hegaldiZerbitzu.getHegaldiIrteraData());
	}

	@Test
	public void testSetHegaldiIrteraData() {
		Date newDate = Date.valueOf("2025-02-01");
		hegaldiZerbitzu.setHegaldiIrteraData(newDate);
		assertEquals(newDate, hegaldiZerbitzu.getHegaldiIrteraData());
	}

	@Test
	public void testGetHegaldiIrteeraOrdutegia() {
		assertEquals(hegaldiIrteeraOrdutegia, hegaldiZerbitzu.getHegaldiIrteeraOrdutegia());
	}

	@Test
	public void testSetHegaldiIrteeraOrdutegia() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHegaldiBidaiarenIraupena() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHegaldiBidaiarenIraupena() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHegaldiPrezioa() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHegaldiPrezioa() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHegaldiJatorrizkoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHegaldiJatorrizkoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHegaldiHelmugakoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHegaldiHelmugakoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAirelinaKodea() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAirelinaKodea() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHegaldiKodeaEtorri() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHegaldiKodeaEtorri() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetItzuleraOrdua() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetItzuleraOrdua() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEtorriaEguna() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEtorriaEguna() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBueltakoIraupena() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBueltakoIraupena() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJoanJatorrizkoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetJoanJatorrizkoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJoanHelmugakoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetJoanHelmugakoAireportua() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBueltakoAirelineaKodea() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBueltakoAirelineaKodea() {
		fail("Not yet implemented");
	}
}
