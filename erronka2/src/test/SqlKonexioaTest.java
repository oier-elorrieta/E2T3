package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import modelo.sql.SqlKonexioa;

public class SqlKonexioaTest {
    
    private SqlKonexioa sqlKonexioa;

    // Testen aurretik setUp metodoa exekutatuko da
    @Before
    public void setUp() throws Exception {
        // SqlKonexioa objektu bat sortu
        sqlKonexioa = new SqlKonexioa();
    }

    // konexioaIreki metodoa testatzen du
    @Test
    public void testKonexioaIreki() throws SQLException {
        // Konexioa ireki
        sqlKonexioa.konexioaIreki();
        // Lortu konexioa
        Connection konexioa = sqlKonexioa.konektatuta;
        // Egiaztatu konexioa null ez dela ireki ondoren
        assertNotNull("Konexioak ez luke Null izan behar ireki ondoren", konexioa);
        // Egiaztatu konexioa itxita ez dagoela ireki ondoren
        assertFalse("Konexioa ez litzateke itxi behar ireki ondoren", konexioa.isClosed());
        // Itxi konexioa
        sqlKonexioa.konexioaItxi();
    }

    // konexioaItxi metodoa testatzen du
    @Test
    public void testKonexioaItxi() throws SQLException {
        // Konexioa ireki
        sqlKonexioa.konexioaIreki();
        // Konexioa itxi
        sqlKonexioa.konexioaItxi();
        // Lortu konexioa
        Connection konexioa = sqlKonexioa.konektatuta;
        // Egiaztatu konexioa itxita dagoela
        assertTrue("Itxita egon behar zen", konexioa.isClosed());
    }
}
