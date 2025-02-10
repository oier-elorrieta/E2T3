package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Agentzia_Motak;

public class Agentzia_MotakTest {

    private Agentzia_Motak agentziaMotak;

    @Before
    public void setUp() {
        agentziaMotak = new Agentzia_Motak("A1", "Mayorista");
    }

    

    @Test
    public void testGetSetAgentziaMKodea() {
    	  agentziaMotak.setAgentziaMKodea("A2");
          assertEquals("A2", agentziaMotak.getAgentziaMKodea());
    }

   

    @Test
    public void testGetsetDeskribapena() {
    	 agentziaMotak.setDeskribapena("Minorista");
         assertEquals("Minorista", agentziaMotak.getDeskribapena());
    }

   

    @Test
    public void testToString() {
        String expected = "Agentzia_Motak [agentziaMKodea=A1, deskribapena=Mayorista]";
        assertEquals(expected, agentziaMotak.toString());
    }
}
