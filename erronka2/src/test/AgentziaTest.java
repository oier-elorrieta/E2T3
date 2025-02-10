package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Agentzia;

public class AgentziaTest {

    private Agentzia agentzia;

    @Before
    public void setUp() {
        agentzia = new Agentzia(1, "L3", "www.www.com", "urdina", "admin", "A1", "admin");
    }

    @Test
    public void testAgentzia() {
    	Agentzia agentzia = new Agentzia();
    	
    	
    }
   
    @Test
    public void testGetSetAgentziaKodea() {
    	 agentzia.setAgentziaKodea(2);
         assertEquals(2, agentzia.getAgentziaKodea());
    }


    @Test
    public void testGetSetLangileKopKod() {
    	 agentzia.setLangileKopKod("L10");
         assertEquals("L10", agentzia.getLangileKopKod());
    }

   

    @Test
    public void testGetSetLogoa() {
    	agentzia.setLogoa("logo.png");
        assertEquals("logo.png", agentzia.getLogoa());
    }

   

    @Test
    public void testGetSetMarkarenKolorea() {
    	agentzia.setMarkarenKolorea("gorria");
        assertEquals("gorria", agentzia.getMarkarenKolorea());
    }

   

    @Test
    public void testGetIzena() {
    	  agentzia.setIzena("alexjav");
          assertEquals("alexjav", agentzia.getIzena());
    }

 

    @Test
    public void testGetSetAgentziaMKod() {
    	 agentzia.setAgentziaMKod("A2");
         assertEquals("A2", agentzia.getAgentziaMKod());
    }

    

    @Test
    public void testGetSetPasahitza() {
    	agentzia.setPasahitza("alexjav");
        assertEquals("alexjav", agentzia.getPasahitza());
    }

    

    @Test
    public void testToString() {
        String expected = "Agentzia [agentziaKodea=1, langileKopKod=L3, logoa=www.www.com, markarenKolorea=urdina, izena=admin, agentziaMKod=A1, pasahitza=admin]";
        assertEquals(expected, agentzia.toString());
    }
}
