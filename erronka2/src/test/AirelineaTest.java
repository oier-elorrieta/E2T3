package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Airelinea;

public class AirelineaTest {

	private Airelinea airelinea;
	@Before
	public void setUp() throws Exception {
		airelinea = new Airelinea("A1","Loiu","H1");
	}

	

	@Test
	public void testGetSetAirelineaKodea() {
		airelinea.setAirelineaKodea("A2");
		assertEquals("A2",airelinea.getAirelineaKodea());
	}

	
	@Test
	public void testGetSetAirelineIzena() {
		airelinea.setAirelineIzena("Santander");;
		assertEquals("Santander",airelinea.getAirelineIzena());
	
	}

	

	@Test
	public void testGetSetHerrialdeKodea() {
		airelinea.setHerrialdeKodea("H2");
		assertEquals("H2",airelinea.getHerrialdeKodea());
	
	}

	

}
