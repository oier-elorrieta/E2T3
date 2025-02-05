package test;

import org.junit.Test;

import modelo.Aireportu;

import static org.junit.Assert.*;

import org.junit.Before;

public class TestAeroportu {
	
	 private Aireportu aeroportu;

	    @Before
	    public void setUp() {
	        aeroportu = new Aireportu("BIO","Bilbao");
	    }

	    @Test
	    public void testGetAireportuKodea() {
	        assertEquals("BIO", aeroportu.getAireportuKodea());
	    }

	    @Test
	    public void testSetAireportuKodea() {
	        aeroportu.setAireportuKodea("MAD");
	        assertEquals("MAD", aeroportu.getAireportuKodea());
	    }

	    @Test
	    public void testGetHiria() {
	        assertEquals("Bilbao", aeroportu.getHiria());
	    }

	    @Test
	    public void testSetHiria() {
	        aeroportu.setHiria("Madrid");
	        assertEquals("Madrid", aeroportu.getHiria());
	    }

	    @Test
	    public void testToString() {
	        String expected = "Aeroportu [aireportuKodea=BIO, hiria=Bilbao]";
	        assertEquals(expected, aeroportu.toString());
	    }
    
}
