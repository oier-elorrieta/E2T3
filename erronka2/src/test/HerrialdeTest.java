package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Herrialde;

public class HerrialdeTest {

	private Herrialde herrialde;

	@Before
	public void setUp() {
		herrialde = new Herrialde("ES", "España");
	}

	
	

	@Test
	public void testGetSetHerrialdeKodea() {
		herrialde.setHerrialdeKodea("FR");
		assertEquals("FR", herrialde.getHerrialdeKodea());
	}

	

	@Test
	public void testGetSetHelmuga() {
		herrialde.setHelmuga("Francia");
		assertEquals("Francia", herrialde.getHelmuga());
	}

	@Test
	public void testToString() {
		String expected = "Herrialde [herrialdeKodea=ES, helmuga=España]";
		assertEquals(expected, herrialde.toString());
	}

}
