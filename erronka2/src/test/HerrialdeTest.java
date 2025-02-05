package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Herrialde;

public class HerrialdeTest {

	private Herrialde herrialde;

	@Before
	public void setUp() {
		herrialde = new Herrialde("ES", "España");
	}

	@Test
	public void testHerrialde() {
		Herrialde herrialde = new Herrialde("FR", "Francia");
		assertNotNull(herrialde);
		assertEquals("FR", herrialde.getHerrialdeKodea());
		assertEquals("Francia", herrialde.getHelmuga());
	}

	@Test
	public void testGetHerrialdeKodea() {
		assertEquals("ES", herrialde.getHerrialdeKodea());
	}

	@Test
	public void testSetHerrialdeKodea() {
		herrialde.setHerrialdeKodea("FR");
		assertEquals("FR", herrialde.getHerrialdeKodea());
	}

	@Test
	public void testGetHelmuga() {
		assertEquals("España", herrialde.getHelmuga());
	}

	@Test
	public void testSetHelmuga() {
		herrialde.setHelmuga("Francia");
		assertEquals("Francia", herrialde.getHelmuga());
	}

	@Test
	public void testToString() {
		String expected = "Herrialde [herrialdeKodea=ES, helmuga=España]";
		assertEquals(expected, herrialde.toString());
	}

}
