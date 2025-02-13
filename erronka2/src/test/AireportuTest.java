package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Aireportu;

public class AireportuTest {
	
	 private Aireportu aeroportu;

	    @Before
	    public void setUp() {
	        aeroportu = new Aireportu("BIO","Bilbao");
	    }

	    @Test
	    public void testGetSetAireportuKodea() {
	    	aeroportu.setAireportuKodea("MAD");
	        assertEquals("MAD", aeroportu.getAireportuKodea());
	    }

	    

	    @Test
	    public void testGetSetHiria() {
	    	aeroportu.setHiria("Madrid");
	        assertEquals("Madrid", aeroportu.getHiria());
	    }

	  

	    @Test
	    public void testToString() {
	        String expected = "Aeroportu [aireportuKodea=BIO, hiria=Bilbao]";
	        assertEquals(expected, aeroportu.toString());
	    }
    
}
