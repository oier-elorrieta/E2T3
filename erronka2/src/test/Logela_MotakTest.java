package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Logela_motak;

public class Logela_MotakTest {
	private Logela_motak logela_motak;
	@Before
	public void setUp() throws Exception {
		logela_motak = new Logela_motak("L1","banakakoa");
	}

	@Test
	public void testGetSetLogelaKodea() {
		logela_motak.setLogelaKodea("L2");
		assertEquals("L2",logela_motak.getLogelaKodea());
	}

	

	@Test
	public void testGetSetLogelaDeskribapena() {
		logela_motak.setLogelaDeskribapena("Bikoitza");
		assertEquals("Bikoitza",logela_motak.getLogelaDeskribapena());
	}

}
