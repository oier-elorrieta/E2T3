package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Bidai_Motak;

public class Bidaia_MotakTest {
	private Bidai_Motak bidai_motak;
	@Before
	public void setUp() throws Exception {
		bidai_motak= new Bidai_Motak("B1","algo");
	}

	

	@Test
	public void testGetSetBidaiKodea() {
		bidai_motak.setBidaiKodea("B2");
		assertEquals("B2",bidai_motak.getBidaiKodea());
	}

	

	@Test
	public void testGetDeskribapena() {
		bidai_motak.setDeskribapena("otra cosa");
		assertEquals("otra cosa",bidai_motak.getDeskribapena());
	
	}

	

	@Test
	public void testToString() {
		assertEquals("Herrialde [BidaiKodea=B1"   ", Deskribapena=" "]", null);
	}

}
