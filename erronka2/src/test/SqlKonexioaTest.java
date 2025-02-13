package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import modelo.sql.SqlKonexioa;


public class SqlKonexioaTest {
	
	private SqlKonexioa sqlKonexioa;
	
	@Before
	public void setUp() throws Exception {
		 sqlKonexioa = new SqlKonexioa();
	}

	

	@Test
	public void testKonexioaIreki() throws SQLException {
		sqlKonexioa.konexioaIreki();
		Connection konexioa = sqlKonexioa.konektatuta;
		assertNotNull("Konexioak ez luke Null izan behar ireki ondoren", konexioa);
		assertFalse("Konexioa ez litzateke itxi behar ireki ondoren", konexioa.isClosed());
		sqlKonexioa.konexioaItxi();
	}

	@Test
	public void testKonexioaItxi() throws SQLException {
		sqlKonexioa.konexioaIreki();
		sqlKonexioa.konexioaItxi();
		Connection konexioa = sqlKonexioa.konektatuta;
		assertTrue("Itxita egon behar zen", konexioa.isClosed());
	}

	

}
