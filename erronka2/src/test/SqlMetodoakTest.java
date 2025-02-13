package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import modelo.pojo.Agentzia;
import modelo.pojo.Agentzia_Motak;
import modelo.pojo.Airelinea;
import modelo.pojo.Aireportu;
import modelo.pojo.Bidai_Motak;
import modelo.pojo.Bidaia;
import modelo.pojo.Herrialde;
import modelo.pojo.Lang_kopurua;
import modelo.pojo.Logela_motak;
import modelo.pojo.Zerbitzu;
import modelo.sql.SqlMetodoak;

public class SqlMetodoakTest {

	private Connection connection;
	private SqlMetodoak sqlMetodoak;

	// Testen aurretik setUp metodoa exekutatuko da
	@Before
	public void setUp() throws Exception {
		// SqlMetodoak objektu bat sortu
		sqlMetodoak = new SqlMetodoak();
	}

	// loginKonparatu metodoa testatu
	@Test
	public void testLoginKomparatu() {
		String erabiltzailea = "admin";
		String pasahitza = "admin";

		// Testeatzeko metodoari deia egin
		sqlMetodoak.loginKonparatu(erabiltzailea, pasahitza);

		// Cacheak espero den agentzia daukan egiaztatu
		Agentzia agentzia = sqlMetodoak.getCache().getAgentzia();
		assertNotNull(agentzia);
		assertEquals(erabiltzailea, agentzia.getIzena());
		assertEquals(pasahitza, agentzia.getPasahitza());
	}

	// ezabatuBidaia metodoa testatu
	@Test
	public void testEzabatuBidaia() throws SQLException {
		Statement statement = connection.createStatement();
		Date bidaiHasiera = Date.valueOf("2025-02-10");
		Date bidaiAmaiera = Date.valueOf("2025-02-20");
		Bidaia bidaia = new Bidaia(9, "bidaiaezabatu", "Bidaiaezabatu deskribapena", "Ez Barne", bidaiHasiera,
				bidaiAmaiera, "ES", "B9", 100);
		statement.execute(
				"INSERT INTO bidaia (kodea, izena, deskribapena, ezBarne, bidaiHasiera, bidaiAmaiera, herrialdeKod, bidaiaMKod, agentziaKod) VALUES ("
						+ bidaia.getBidaiKodea() + ", '" + bidaia.getIzena() + "', '" + bidaia.getDeskribapena()
						+ "', '" + bidaia.getEzBarne() + "', '" + bidaia.getBidaiHasiera() + "', '"
						+ bidaia.getBidaiAmaiera() + "', '" + bidaia.getHerrialdeKod() + "', '" + bidaia.getBidaiaMKod()
						+ "', " + bidaia.getAgentziaKod() + ")");

		sqlMetodoak.ezabatuBidaia(9);

		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM bidaia WHERE kodea = 9");
			assertFalse(resultSet.next()); // kodea = 9 duten errenkadarik ez dagoela egiaztatu
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

	// ezabatuZerbitzua metodoa testatu
	@Test
	public void testEzabatuZerbitzua() throws SQLException {
		Statement statement = connection.createStatement();
		Date ostatuSarreraEguna = Date.valueOf("2025-02-10");
		Date ostatuIrteraEguna = Date.valueOf("2025-02-20");

		Zerbitzu zerbitzu = new Zerbitzu(9, "Servicio de Prueba", 1, 100.0f, ostatuSarreraEguna, ostatuIrteraEguna,
				"Bilbao", "L1");

		statement.execute(
				"INSERT INTO zerbitzuak (kodea, izena, bidaiMotaKodea, ostatuPrezioa, ostatuSarreraEguna, ostatuIrteraEguna, hiria, logelaMKodea) VALUES ("
						+ zerbitzu.getZerbitzuKodea() + ", '" + zerbitzu.getZerbitzuIzena() + "', "
						+ zerbitzu.getBidaiKodea() + ", " + zerbitzu.getOstatuPrezioa() + ", '"
						+ zerbitzu.getOstatuSarreraEguna() + "', '" + zerbitzu.getOstatuIrteraEguna() + "', '"
						+ zerbitzu.getHiria() + "', '" + zerbitzu.getLogelaMKodea() + "')");

		sqlMetodoak.ezabatuZerbitzua(1);

		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM zerbitzuak WHERE kodea = 9");
			assertFalse(resultSet.next()); // kodea = 9 duten errenkadarik ez dagoela egiaztatu
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

	// zerbitzuJoanEgin metodoa testatu
	@Test
	public void testZerbitzuJoanEgin() throws SQLException {
		sqlMetodoak.zerbitzuJoanEgin(9, "JFK", "LAX", "2025/02/15", "FL123", "Delta", 500.0f, Time.valueOf("10:00:00"),
				Time.valueOf("05:00:00"));

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM zerbitzuak WHERE kodea = 9");
			assertTrue(resultSet.next()); // kodea = 9 duen errenkada existitzen dela egiaztatu
			assertEquals("Hegaldia", resultSet.getString("izena"));
			assertEquals("JFK", resultSet.getString("jatorrizkoAireportua")); // Gehitu beharrezko egiaztapen gehiago
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

	// zerbitzuJoanEtorriEgin metodoa testatu
	@Test
	public void testZerbitzuJoanEtorriEgin() throws SQLException {

		sqlMetodoak.zerbitzuJoanEtorriEgin(9, "JFK", "LAX", "2025/02/15", "FL123", "Delta", 500.0f,
				Time.valueOf("10:00:00"), Time.valueOf("05:00:00"), "LAX", "JFK", "2025/02/20", "FL321", "Delta",
				Time.valueOf("15:00:00"), Time.valueOf("05:00:00"));

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM zerbitzuak WHERE kodea = 9");
			assertTrue(resultSet.next()); // kodea = 9 duen errenkada existitzen dela egiaztatu
			assertEquals("Joan_Etorri", resultSet.getString("izena"));

			resultSet = statement.executeQuery("SELECT * FROM hegaldia WHERE zerbitzu_kodea = 9");
			assertTrue(resultSet.next()); // zerbitzu_kodea = 9 duen errenkada hegaldia taulan existitzen dela egiaztatu
			assertEquals("JFK", resultSet.getString("jatorrizkoAireportua")); // Gehitu beharrezko egiaztapen gehiago

			resultSet = statement.executeQuery("SELECT * FROM joan_eta_etorri WHERE zerbitzu_kodea = 9");
			assertTrue(resultSet.next()); // zerbitzu_kodea = 9 duen errenkada joan_eta_etorri taulan existitzen dela
											// egiaztatu
			assertEquals("LAX", resultSet.getString("joanJatorrizkoAireportua")); // Gehitu beharrezko egiaztapen
																					// gehiago
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

//zerbitzuOstatuEgin metodoa testatu
	@Test
	public void testZerbitzuOstatuEgin() throws SQLException {
		sqlMetodoak.zerbitzuOstatuEgin(9, "L1", "Madrid", 200.0f, "2025/02/10", "2025/02/20");

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM zerbitzuak WHERE kodea = 9");
			assertTrue(resultSet.next()); // kodea = 9 duen errenkada existitzen dela egiaztatu
			assertEquals("Ostatua", resultSet.getString("izena"));

			resultSet = statement.executeQuery("SELECT * FROM ostatua WHERE zerbitzu_kodea = 9");
			assertTrue(resultSet.next()); // zerbitzu_kodea = 9 duen errenkada ostatua taulan existitzen dela egiaztatu
			assertEquals("Madrid", resultSet.getString("hiria")); // Gehitu beharrezko egiaztapen gehiago
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

// zerbitzuJardueraEgin metodoa testatu
	@Test
	public void testZerbitzuJardueraEgin() {
		sqlMetodoak.zerbitzuJardueraEgin(1, "Visita Guiada", "Visita guiada por la ciudad", 50.0f, "2025/02/15");

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM zerbitzuak WHERE kodea = 1");
			assertTrue(resultSet.next()); // kodea = 1 duen errenkada existitzen dela egiaztatu
			assertEquals("Visita Guiada", resultSet.getString("izena"));

			resultSet = statement.executeQuery("SELECT * FROM beste_batzuk WHERE zerbitzu_kodea = 1");
			assertTrue(resultSet.next()); // zerbitzu_kodea = 1 duen errenkada beste_batzuk taulan existitzen dela
											// egiaztatu
			assertEquals("Visita guiada por la ciudad", resultSet.getString("deskribapena")); // Gehitu beharrezko
																								// egiaztapen gehiago
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

// erregistroEgin metodoa testatu
	@Test
	public void testErregistroEgin() {
		sqlMetodoak.erregistroEgin("Agentzia Prueba", "Rojo", "L1", "AM1", "logo.png", "testpassword");

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM agentzia WHERE izena = 'Agentzia Prueba'");
			assertTrue(resultSet.next()); // 'Agentzia Prueba' izena duen errenkada existitzen dela egiaztatu
			assertEquals("Agentzia Prueba", resultSet.getString("izena"));
			assertEquals("Rojo", resultSet.getString("markaren_kolorea"));
			// Gehitu beharrezko egiaztapen gehiago
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

// bidaiaEgin metodoa testatu
	@Test
	public void testBidaiaEgin() {
		sqlMetodoak.bidaiaEgin("Viaje de Prueba", "Descripción del viaje", "No incluye transporte", "2025/02/10",
				"2025/02/20", "ES", "BT", 1);

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM bidaia WHERE bidaiaren_izena = 'Viaje de Prueba'");
			assertTrue(resultSet.next()); // 'Viaje de Prueba' izena duen errenkada existitzen dela egiaztatu
			assertEquals("Viaje de Prueba", resultSet.getString("bidaiaren_izena"));
			assertEquals("Descripción del viaje", resultSet.getString("deskribapena"));
			// Gehitu beharrezko egiaztapen gehiago
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errorea gertatu da testaren egiaztapenean");
		}
	}

// aireportuaEduki metodoa testatu
	@Test
	public void testAireportuaEduki() throws SQLException {

		ArrayList<Aireportu> aireportuak = sqlMetodoak.aireportuaEduki();

		assertNotNull(aireportuak);
		assertEquals(2, aireportuak.size()); // Ziurtatu tamaina espero dena dela
		Aireportu aireportu1 = aireportuak.get(0);
		assertEquals("BIO", aireportu1.getAireportuKodea());
		assertEquals("Bilbao", aireportu1.getHiria());

		Aireportu aireportu2 = aireportuak.get(1);
		assertEquals("BCN", aireportu2.getAireportuKodea());
		assertEquals("Barcelona", aireportu2.getHiria());
	}

// airelineaEduki metodoa testatu
	@Test
	public void testAirelineaEduki() {
		ArrayList<Airelinea> airelineak = sqlMetodoak.airelineaEduki();

		assertNotNull(airelineak);
		assertEquals(2, airelineak.size()); // Ziurtatu tamaina espero dena dela
		Airelinea airelinea1 = airelineak.get(0);
		assertEquals("DL", airelinea1.getAirelineaKodea());
		assertEquals("Delta", airelinea1.getAirelineIzena());
		assertEquals("US", airelinea1.getHerrialdeKodea());

		Airelinea airelinea2 = airelineak.get(1);
		assertEquals("IB", airelinea2.getAirelineaKodea());
		assertEquals("Iberia", airelinea2.getAirelineIzena());
		assertEquals("ES", airelinea2.getHerrialdeKodea());
	}

//bidaiakEduki metodoa testatu
	@Test
	public void testBidaiakEduki() {
		ArrayList<Bidaia> bidaiak = sqlMetodoak.bidaiakEduki();

		assertNotNull(bidaiak);
		assertEquals(1, bidaiak.size()); // Ziurtatu tamaina espero dena dela

		Bidaia bidaia = bidaiak.get(0);
		assertEquals(1, bidaia.getBidaiKodea());
		assertEquals("Viaje de Prueba", bidaia.getIzena());

		ArrayList<Zerbitzu> zerbitzuak = bidaia.getZerbitzuak();
		assertNotNull(zerbitzuak);
		assertEquals(2, zerbitzuak.size()); // Ziurtatu zerbitzu kopurua espero dena dela

		Zerbitzu zerbitzu1 = zerbitzuak.get(0);
		assertEquals(1, zerbitzu1.getZerbitzuKodea());
		assertEquals("Hegaldia", zerbitzu1.getZerbitzuIzena());

		Zerbitzu zerbitzu2 = zerbitzuak.get(1);
		assertEquals(2, zerbitzu2.getZerbitzuKodea());
		assertEquals("Ostatua", zerbitzu2.getZerbitzuIzena());
	}

// langKopEduki metodoa testatu
	@Test
	public void testLangKopEduki() {
		ArrayList<Lang_kopurua> langKopuruak = sqlMetodoak.langKopEduki();

		assertNotNull(langKopuruak);
		assertEquals(2, langKopuruak.size()); // Ziurtatu tamaina espero dena dela

		Lang_kopurua langKop1 = langKopuruak.get(0);
		assertEquals("L1", langKop1.getLangKopKodea());
		assertEquals("1-10 empleados", langKop1.getDeskribapena());

		Lang_kopurua langKop2 = langKopuruak.get(1);
		assertEquals("L2", langKop2.getLangKopKodea());
		assertEquals("11-50 empleados", langKop2.getDeskribapena());
	}

// logelaMotaEduki metodoa testatu
	@Test
	public void testLogelaMotaEduki() {
		ArrayList<Logela_motak> logelaMotak = sqlMetodoak.logelaMotaEduki();

		assertNotNull(logelaMotak);
		assertEquals(2, logelaMotak.size()); // Ziurtatu tamaina espero dena dela

		Logela_motak logelaMota1 = logelaMotak.get(0);
		assertEquals("L1", logelaMota1.getLogelaKodea());
		assertEquals("Individual", logelaMota1.getLogelaDeskribapena());

		Logela_motak logelaMota2 = logelaMotak.get(1);
		assertEquals("L2", logelaMota2.getLogelaKodea());
		assertEquals("Doble", logelaMota2.getLogelaDeskribapena());
	}

// agentziaMotaEduki metodoa testatu
	@Test
	public void testAgentziaMotaEduki() {
		ArrayList<Agentzia_Motak> agentziaMotak = sqlMetodoak.agentziaMotaEduki();

		assertNotNull(agentziaMotak);
		assertEquals(2, agentziaMotak.size()); // Ziurtatu tamaina espero dena dela

		Agentzia_Motak agentziaMota1 = agentziaMotak.get(0);
		assertEquals("AM1", agentziaMota1.getAgentziaMKodea());
		assertEquals("Agencia de Viajes", agentziaMota1.getDeskribapena());

		Agentzia_Motak agentziaMota2 = agentziaMotak.get(1);
		assertEquals("AM2", agentziaMota2.getAgentziaMKodea());
		assertEquals("Agencia de Transporte", agentziaMota2.getDeskribapena());
	}

// bidaiMotaEduki metodoa testatu
	@Test
	public void testBidaiMotaEduki() {
		ArrayList<Bidai_Motak> bidaiMotak = sqlMetodoak.bidaiMotaEduki();

		assertNotNull(bidaiMotak);
		assertEquals(2, bidaiMotak.size()); // Ziurtatu tamaina espero dena dela

		Bidai_Motak bidaiMota1 = bidaiMotak.get(0);
		assertEquals("BT1", bidaiMota1.getBidaiKodea());
		assertEquals("Viaje de Placer", bidaiMota1.getDeskribapena());

		Bidai_Motak bidaiMota2 = bidaiMotak.get(1);
		assertEquals("BT2", bidaiMota2.getBidaiKodea());
		assertEquals("Viaje de Negocios", bidaiMota2.getDeskribapena());
	}

// herrialdeMotaEduki metodoa testatu
	@Test
	public void testHerrialdeMotaEduki() {
		ArrayList<Herrialde> herrialdeMotak = sqlMetodoak.herrialdeMotaEduki();

		assertNotNull(herrialdeMotak);
		assertEquals(2, herrialdeMotak.size()); // Ziurtatu tamaina espero dena dela

		Herrialde herrialde1 = herrialdeMotak.get(0);
		assertEquals("ES", herrialde1.getHerrialdeKodea());
		assertEquals("España", herrialde1.getHelmuga());

		Herrialde herrialde2 = herrialdeMotak.get(1);
		assertEquals("US", herrialde2.getHerrialdeKodea());
		assertEquals("Estados Unidos", herrialde2.getHelmuga());
	}
}
