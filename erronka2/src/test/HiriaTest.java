package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelo.Hiria;

public class HiriaTest {

    private Hiria hiria;

    @Before
    public void setUp() {
        hiria = new Hiria();
        hiria.setHiriKode(1);
        hiria.setIzena("Bilbo");
    }

    @Test
    public void testGetHiriKode() {
        assertEquals(1, hiria.getHiriKode());
    }

    @Test
    public void testSetHiriKode() {
        hiria.setHiriKode(2);
        assertEquals(2, hiria.getHiriKode());
    }

    @Test
    public void testGetIzena() {
        assertEquals("Bilbo", hiria.getIzena());
    }

    @Test
    public void testSetIzena() {
        hiria.setIzena("Donostia");
        assertEquals("Donostia", hiria.getIzena());
    }

    @Test
    public void testToString() {
        String expected = "Hiria [hiriKode=1, izena=Bilbo]";
        assertEquals(expected, hiria.toString());
    }
}
