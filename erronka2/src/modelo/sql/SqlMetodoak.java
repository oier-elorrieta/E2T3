package modelo.sql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import modelo.pojo.Agentzia;
import modelo.pojo.Agentzia_Motak;
import modelo.pojo.Airelinea;
import modelo.pojo.Aireportu;
import modelo.pojo.Bidai_Motak;
import modelo.pojo.Bidaia;
import modelo.pojo.Cache;
import modelo.pojo.Herrialde;
import modelo.pojo.Lang_kopurua;
import modelo.pojo.Logela_motak;
import modelo.pojo.Zerbitzu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SqlMetodoak {

	SqlKonexioa konexioa = new SqlKonexioa();

	private String sql = "";
	private Cache cache = new Cache();

	public Cache getCache() {
		return cache;
	}

	public void loginKomparatu(String user, String pass) {

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM agentzia WHERE izena = '" + user + "' and pasahitza = '" + pass + "'";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int kodea = resultSet.getInt(1);
				String langileKopKod = resultSet.getString(2);
				String logoa = resultSet.getString(3);
				String markarenKolorea = resultSet.getString(4);
				String izena = resultSet.getString(5);
				String agentziaMKod = resultSet.getString(6);
				String pasahitza = resultSet.getString(7);

				Agentzia agentzia = new Agentzia(kodea, langileKopKod, logoa, markarenKolorea, izena, agentziaMKod,
						pasahitza);
				cache.setAgentzia(agentzia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// cuando acaba el try catch da igual donde acabe siempre va a hacer el finally
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	public void ezabatuBidaia(int bidaiKodea) {
		try {
			konexioa.konexioaIreki(); // Abre la conexión

			// Consulta DELETE con parámetro ? (seguro)
			String sql = "DELETE FROM bidaia WHERE kodea = ?";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);

			// Asignamos el parámetro bidaiKodea a la consulta
			preparedStatement.setInt(1, bidaiKodea);

			// Ejecutamos el DELETE y verificamos cuántas filas se eliminaron
			int filasAfectadas = preparedStatement.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Ezabatuta.");
			} else {
				System.out.println("Ez da ezabatu.");
			}

			// Cierra el PreparedStatement después de su uso
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace(); // Muestra el error en consola
		} finally {
			konexioa.konexioaItxi(); // Cierra la conexión
		}
	}

	public void ezabatuZerbitzua(int zerbitzuKod) {
		try {
			konexioa.konexioaIreki(); // Abre la conexión

			// Consulta DELETE con parámetro ? (seguro)
			String sql = "DELETE FROM zerbitzuak WHERE kodea = ?";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);

			// Asignamos el parámetro bidaiKodea a la consulta
			preparedStatement.setInt(1, zerbitzuKod);

			// Ejecutamos el DELETE y verificamos cuántas filas se eliminaron
			int filasAfectadas = preparedStatement.executeUpdate();

			if (filasAfectadas > 0) {
				System.out.println("Ezabatuta.");
			} else {
				System.out.println("Ez da ezabatu.");
			}

			// Cierra el PreparedStatement después de su uso
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace(); // Muestra el error en consola
		} finally {
			konexioa.konexioaItxi(); // Cierra la conexión
		}
	}

	public void zerbitzuJoanEgin(int bidaiKodea, String jatorrizkoAireportua, String helmugakoAireportua,
			String sJoanekoData, String hegaldiKodea, String aerolinea, float prezio, Time orduteguia, Time iraupena) {

		Zerbitzu zerbitzuak = new Zerbitzu();

		System.out.println(aerolinea);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		zerbitzuak.setZerbitzuIzena("Hegaldia");
		zerbitzuak.setHegaldiJatorrizkoAireportua(jatorrizkoAireportua);
		zerbitzuak.setHegaldiHelmugakoAireportua(helmugakoAireportua);
		LocalDate irteeraData = LocalDate.parse(sJoanekoData, formatter);
		zerbitzuak.setHegaldiIrteraData(Date.valueOf(irteeraData));
		zerbitzuak.setHegaldiKodea(hegaldiKodea);
		zerbitzuak.setAirelinaKodea(aerolinea);
		zerbitzuak.setHegaldiPrezioa(prezio);
		zerbitzuak.setHegaldiIrteeraOrdutegia(orduteguia);
		zerbitzuak.setHegaldiBidaiarenIraupena(iraupena);
		zerbitzuak.setBidaiKodea(bidaiKodea);

		int zerbitzuKodea = -1; // Variable para guardar el ID generado

		try {
			konexioa.konexioaIreki();

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.
			System.out.println(sql);
			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Error: No se pudo obtener el ID generado.");
			}

			String sql2 = "INSERT INTO hegaldia VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getHegaldiKodea()
					+ "', '" + zerbitzuak.getHegaldiIrteraData() + "', '" + zerbitzuak.getHegaldiIrteeraOrdutegia()
					+ "', '" + zerbitzuak.getHegaldiBidaiarenIraupena() + "', '" + zerbitzuak.getHegaldiPrezioa()
					+ "', '" + zerbitzuak.getHegaldiJatorrizkoAireportua() + "', '"
					+ zerbitzuak.getHegaldiHelmugakoAireportua() + "', '" + zerbitzuak.getAirelinaKodea() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);
			System.out.println(sql2);
			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	public void zerbitzuJoanEtorriEgin(int bidaiKodea, String jatorrizkoAireportua, String helmugakoAireportua,
			String sJoanekoData, String hegaldiKodea, String aerolinea, float prezio, Time orduteguia, Time iraupena,
			String jatorrizkoAireportuaE, String helmugakoAireportuaE, String sJoanekoDataE, String hegaldiKodeaE,
			String aerolineaE, float prezioE, Time orduteguiaE, Time iraupenaE) {

		Zerbitzu zerbitzuak = new Zerbitzu();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		zerbitzuak.setZerbitzuIzena("Joan_Etorri");

		zerbitzuak.setHegaldiJatorrizkoAireportua(jatorrizkoAireportua);
		zerbitzuak.setHegaldiHelmugakoAireportua(helmugakoAireportua);
		LocalDate irteeraData = LocalDate.parse(sJoanekoData, formatter);
		zerbitzuak.setHegaldiIrteraData(Date.valueOf(irteeraData));
		zerbitzuak.setHegaldiKodea(hegaldiKodea);
		zerbitzuak.setAirelinaKodea(aerolinea);
		zerbitzuak.setHegaldiPrezioa(prezio);
		zerbitzuak.setHegaldiIrteeraOrdutegia(orduteguia);
		zerbitzuak.setHegaldiBidaiarenIraupena(iraupena);

		zerbitzuak.setJoanJatorrizkoAireportua(jatorrizkoAireportuaE);
		zerbitzuak.setJoanHelmugakoAireportua(helmugakoAireportuaE);
		LocalDate irteeraDataE = LocalDate.parse(sJoanekoDataE, formatter);
		zerbitzuak.setEtorriaEguna(Date.valueOf(irteeraDataE));
		zerbitzuak.setHegaldiKodeaEtorri(hegaldiKodeaE);
		zerbitzuak.setBueltakoAirelineaKodea(aerolineaE);
		zerbitzuak.setHegaldiPrezioa(prezioE);
		zerbitzuak.setItzuleraOrdua(orduteguiaE);
		zerbitzuak.setBueltakoIraupena(iraupenaE);
		zerbitzuak.setBidaiKodea(bidaiKodea);

		int zerbitzuKodea = -1; // Variable para guardar el ID generado

		try {
			konexioa.konexioaIreki();

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.
			System.out.println(sql);
			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Error: No se pudo obtener el ID generado.");
			}

			String sql2 = "INSERT INTO hegaldia VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getHegaldiKodea()
					+ "', '" + zerbitzuak.getHegaldiIrteraData() + "', '" + zerbitzuak.getHegaldiIrteeraOrdutegia()
					+ "', '" + zerbitzuak.getHegaldiBidaiarenIraupena() + "', '" + zerbitzuak.getHegaldiPrezioa()
					+ "', '" + zerbitzuak.getHegaldiJatorrizkoAireportua() + "', '"
					+ zerbitzuak.getHegaldiHelmugakoAireportua() + "', '" + zerbitzuak.getAirelinaKodea() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);
			System.out.println(sql2);
			preparedStatement2.executeUpdate();

			String sql3 = "INSERT INTO joan_eta_etorri VALUES ('" + zerbitzuKodea + "', '"
					+ zerbitzuak.getHegaldiKodeaEtorri() + "', '" + zerbitzuak.getItzuleraOrdua() + "', '"
					+ zerbitzuak.getEtorriaEguna() + "', '" + zerbitzuak.getBueltakoIraupena() + "', '"
					+ zerbitzuak.getJoanJatorrizkoAireportua() + "', '" + zerbitzuak.getJoanHelmugakoAireportua()
					+ "', '" + zerbitzuak.getBueltakoAirelineaKodea() + "')";
			PreparedStatement preparedStatement3 = konexioa.konektatuta.prepareStatement(sql3);
			System.out.println(sql3);
			preparedStatement3.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	public void zerbitzuOstatuEgin(int bidaiKodea, String logelaMota, String hiria, float prezioO, String sSarreraDate,
			String sIrteeraDate) {

		Zerbitzu zerbitzuak = new Zerbitzu();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		zerbitzuak.setZerbitzuIzena("Ostatua");
		zerbitzuak.setLogelaMKodea(logelaMota);
		zerbitzuak.setHiria(hiria);
		zerbitzuak.setOstatuPrezioa(prezioO);
		LocalDate sarreraDate = LocalDate.parse(sSarreraDate, formatter);
		zerbitzuak.setOstatuSarreraEguna(Date.valueOf(sarreraDate));
		LocalDate irteeraDate = LocalDate.parse(sIrteeraDate, formatter);
		zerbitzuak.setOstatuIrteraEguna(Date.valueOf(irteeraDate));
		zerbitzuak.setBidaiKodea(bidaiKodea);

		int zerbitzuKodea = -1; // Variable para guardar el ID generado

		try {
			konexioa.konexioaIreki();

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.
			System.out.println(sql);
			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Error: No se pudo obtener el ID generado.");
			}

			String sql2 = "INSERT INTO ostatua VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getOstatuPrezioa()
					+ "', '" + zerbitzuak.getOstatuSarreraEguna() + "', '" + zerbitzuak.getOstatuIrteraEguna() + "', '"
					+ zerbitzuak.getHiria() + "', '" + zerbitzuak.getLogelaMKodea() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);
			System.out.println(sql2);
			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	public void zerbitzuJardueraEgin(int bidaiKodea, String jardueraIzena, String jardueraDeskribapena, float prezioJ,
			String sJardueraDate) {

		Zerbitzu zerbitzuak = new Zerbitzu();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		zerbitzuak.setZerbitzuIzena(jardueraIzena);
		zerbitzuak.setBesteBatzukDeskribapena(jardueraDeskribapena);
		zerbitzuak.setBesteBatzukPrezioa(prezioJ);
		LocalDate jardueraDate = LocalDate.parse(sJardueraDate, formatter);
		zerbitzuak.setEgun(Date.valueOf(jardueraDate));
		zerbitzuak.setBidaiKodea(bidaiKodea);

		int zerbitzuKodea = -1; // Variable para guardar el ID generado

		try {
			konexioa.konexioaIreki();

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.
			System.out.println(sql);
			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Error: No se pudo obtener el ID generado.");
			}

			String sql2 = "INSERT INTO beste_batzuk VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getEgun() + "', '"
					+ zerbitzuak.getBesteBatzukDeskribapena() + "', '" + zerbitzuak.getBesteBatzukPrezioa() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);
			System.out.println(sql2);
			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	public void erregistroEgin(String agentziaIzena, String kolorea, String langile, String agentziaMota, String logoa,
			String pasahitza) {
		Agentzia agentzia = new Agentzia();

		agentzia.setIzena(agentziaIzena);
		agentzia.setMarkarenKolorea(kolorea);
		agentzia.setLangileKopKod(langile);
		agentzia.setAgentziaMKod(agentziaMota);
		agentzia.setLogoa(logoa);
		agentzia.setPasahitza(pasahitza);

		try {
			konexioa.konexioaIreki();

			sql = "INSERT INTO agentzia (langile_kopuru_kodea, logoa, markaren_kolorea, izena, agentzia_m_kodea, pasahitza) VALUES ('"
					+ agentzia.getLangileKopKod() + "', '" + agentzia.getLogoa() + "', '"
					+ agentzia.getMarkarenKolorea() + "', '" + agentzia.getIzena() + "', '" + agentzia.getAgentziaMKod()
					+ "', '" + agentzia.getPasahitza() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	public void bidaiaEgin(String bidaiIzena, String bidaiDeskribapena, String ezBarne, String bidaiHasiera,
			String bidaiAmaiera, String herrialdeKodea, String bidaiMKodea, int agentziaKodea) {
		Bidaia bidaia = new Bidaia();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		bidaia.setIzena(bidaiIzena);
		bidaia.setDeskribapena(bidaiDeskribapena);
		bidaia.setEzBarne(ezBarne);
		LocalDate dataHasiera = LocalDate.parse(bidaiHasiera, formatter);
		bidaia.setBidaiHasiera(Date.valueOf(dataHasiera));
		LocalDate dataAmaiera = LocalDate.parse(bidaiAmaiera, formatter);
		bidaia.setBidaiAmaiera(Date.valueOf(dataAmaiera));
		bidaia.setHerrialdeKod(herrialdeKodea);
		bidaia.setBidaiaMKod(bidaiMKodea);
		bidaia.setAgentziaKod(agentziaKodea);

		try {
			konexioa.konexioaIreki();

			sql = "INSERT INTO bidaia (bidaiaren_izena, deskribapena, Ez_barne, bidai_hasiera, bidai_amaiera, Herrialde_kodea, Bidaia_m_kodea, Agentzia_kodea) VALUES ('"
					+ bidaia.getIzena() + "', '" + bidaia.getDeskribapena() + "', '" + bidaia.getEzBarne() + "', '"
					+ bidaia.getBidaiHasiera() + "', '" + bidaia.getBidaiAmaiera() + "', '" + bidaia.getHerrialdeKod()
					+ "', '" + bidaia.getBidaiaMKod() + "', '" + bidaia.getAgentziaKod() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	public ArrayList<Aireportu> aireportuaEduki() {
		ArrayList<Aireportu> aireportua = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM iata";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String aireportuKodea = resultSet.getString(1);
				String hiria = resultSet.getString(2);

				Aireportu iata = new Aireportu(aireportuKodea, hiria);
				aireportua.add(iata);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

		return aireportua;
	}

	public ArrayList<Airelinea> airelineaEduki() {
		ArrayList<Airelinea> airelineaList = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM airelineak";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String airelineaKodea = resultSet.getString(1);
				String airelineIzena = resultSet.getString(2);
				String herrialdeKodea = resultSet.getString(3);

				Airelinea airelinea = new Airelinea(airelineaKodea, airelineIzena, herrialdeKodea);
				airelineaList.add(airelinea);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

		return airelineaList;
	}

	public ArrayList<Bidaia> bidaiakEduki() {
		ArrayList<Bidaia> bidaiak = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			// Primera consulta: Obtener los viajes
			String sql1 = "SELECT * FROM bidaia WHERE agentzia_kodea = '" + cache.getAgentzia().getAgentziaKodea()
					+ "'";

			PreparedStatement preparedStatement1 = konexioa.konektatuta.prepareStatement(sql1);

			ResultSet resultSet1 = preparedStatement1.executeQuery(sql1);

			while (resultSet1.next()) {
				int bidaiKodea = resultSet1.getInt(1);
				String bidaiIzena = resultSet1.getString(2);
				String bidaiDeskribapena = resultSet1.getString(3);
				String bidaiEzBarne = resultSet1.getString(4);
				Date bidaiHasiera = resultSet1.getDate(5);
				Date bidaiAmaiera = resultSet1.getDate(6);
				String bidaiHerrialdeKod = resultSet1.getString(7);
				String bidaiMotaKod = resultSet1.getString(8);
				int bidaiAgentziaKod = resultSet1.getInt(9);

				// Crear el objeto Bidaia
				Bidaia bidai = new Bidaia(bidaiKodea, bidaiIzena, bidaiDeskribapena, bidaiEzBarne, bidaiHasiera,
						bidaiAmaiera, bidaiHerrialdeKod, bidaiMotaKod, bidaiAgentziaKod);
				bidaiak.add(bidai);

				// Segunda consulta: Obtener los servicios asociados a cada viaje
				String sql2 = "SELECT *" + " FROM zerbitzuak"
						+ " LEFT JOIN hegaldia ON zerbitzuak.Kodea = hegaldia.Zerbitzu_kodea"
						+ " LEFT JOIN joan_eta_etorri ON zerbitzuak.Kodea = joan_eta_etorri.Zerbitzu_kodea"
						+ " LEFT JOIN ostatua ON zerbitzuak.Kodea = ostatua.Zerbitzu_kodea"
						+ " LEFT JOIN beste_batzuk ON zerbitzuak.Kodea = beste_batzuk.Zerbitzu_kodea"
						+ " WHERE Bidaiaren_kodea = '" + bidai.getBidaiKodea() + "'";

				PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);

				ResultSet resultSet2 = preparedStatement2.executeQuery(sql2);

				while (resultSet2.next()) {

					if (resultSet2.getInt(4) != 0 && resultSet2.getInt(4) != resultSet2.getInt(13)) {
						System.out.println("Servicio encontrado para el viaje " + resultSet2.getInt(4));

						int zerbitzuKodeaH = resultSet2.getInt(4);
						String zerbitzuIzenaH = resultSet2.getString(2);
						int bidaiKodeaH = resultSet2.getInt(3);
						String hegaldiKodea = resultSet2.getString(5);
						Date hegaldiIrteraData = resultSet2.getDate(6);
						Time hegaldiIrteeraOrdutegia = resultSet2.getTime(7);
						Time hegaldiBidaiarenIraupena = resultSet2.getTime(8);
						float hegaldiPrezioa = resultSet2.getFloat(9);
						String hegaldiJatorrizkoAireportua = resultSet2.getString(10);
						String hegaldiHelmugakoAireportua = resultSet2.getString(11);
						String airelinaKodea = resultSet2.getString(12);

						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodeaH, zerbitzuIzenaH, bidaiKodeaH, hegaldiKodea,
								hegaldiIrteraData, hegaldiIrteeraOrdutegia, hegaldiBidaiarenIraupena, hegaldiPrezioa,
								hegaldiJatorrizkoAireportua, hegaldiHelmugakoAireportua, airelinaKodea);

						bidai.gehituZerbitzuak(zerbitzuak);

					} else if (resultSet2.getInt(13) != 0 && resultSet2.getInt(13) == resultSet2.getInt(4)) {
						System.out.println("Servicio encontrado para la ida y vuelta " + resultSet2.getInt(13));

						int zerbitzuKodeaHE = resultSet2.getInt(13);
						String zerbitzuIzenaH = resultSet2.getString(2);
						int bidaiKodeaHE = resultSet2.getInt(3);
						String hegaldiKodea = resultSet2.getString(5);
						Date hegaldiIrteraData = resultSet2.getDate(6);
						Time hegaldiIrteeraOrdutegia = resultSet2.getTime(7);
						Time hegaldiBidaiarenIraupena = resultSet2.getTime(8);
						float hegaldiPrezioa = resultSet2.getFloat(9);
						String hegaldiJatorrizkoAireportua = resultSet2.getString(10);
						String hegaldiHelmugakoAireportua = resultSet2.getString(11);
						String airelinaKodea = resultSet2.getString(12);
						String hegaldiKodeaEtorri = resultSet2.getString(14);
						Time itzuleraOrdua = resultSet2.getTime(15);
						Date etorriaEguna = resultSet2.getDate(16);
						Time bueltakoIraupena = resultSet2.getTime(17);
						String joanJatorrizkoAireportua = resultSet2.getString(18);
						String joanHelmugakoAireportua = resultSet2.getString(19);
						String bueltakoAirelineaKodea = resultSet2.getString(20);

						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodeaHE, zerbitzuIzenaH, bidaiKodeaHE, hegaldiKodea,
								hegaldiIrteraData, hegaldiIrteeraOrdutegia, hegaldiBidaiarenIraupena, hegaldiPrezioa,
								hegaldiJatorrizkoAireportua, hegaldiHelmugakoAireportua, airelinaKodea,
								hegaldiKodeaEtorri, itzuleraOrdua, etorriaEguna, bueltakoIraupena,
								joanJatorrizkoAireportua, joanHelmugakoAireportua, bueltakoAirelineaKodea);

						bidai.gehituZerbitzuak(zerbitzuak);
					}

					else if (resultSet2.getInt(21) != 0) {
						System.out.println("Servicio encontrado para el hospedaje " + resultSet2.getInt(21));

						int zerbitzuKodea = resultSet2.getInt(21);
						String zerbitzuIzena = resultSet2.getString(2);
						int bidaiKodeaO = resultSet2.getInt(3);
						float ostatuPrezioa = resultSet2.getFloat(22);
						Date ostatuSarreraEguna = resultSet2.getDate(23);
						Date ostatuIrteraEguna = resultSet2.getDate(24);
						String hiria = resultSet2.getString(25);
						String logelaMKodea = resultSet2.getString(26);

						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodea, zerbitzuIzena, bidaiKodeaO, ostatuPrezioa,
								ostatuSarreraEguna, ostatuIrteraEguna, hiria, logelaMKodea);

						bidai.gehituZerbitzuak(zerbitzuak);

					}

					else if (resultSet2.getInt(27) != 0) {
						System.out.println("Servicio encontrado para el beste " + resultSet2.getInt(27));

						int zerbitzuKodea = resultSet2.getInt(27);
						String zerbitzuIzena = resultSet2.getString(2);
						int bidaiKodeaB = resultSet2.getInt(3);
						Date egun = resultSet2.getDate(28);
						String besteBatzukDeskribapena = resultSet2.getString(29);
						float besteBatzukPrezioa = resultSet2.getFloat(30);

						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodea, zerbitzuIzena, bidaiKodeaB, egun,
								besteBatzukDeskribapena, besteBatzukPrezioa);

						bidai.gehituZerbitzuak(zerbitzuak);

					}

					// Aquí puedes agregar los objetos Zerbitzu a la lista dentro de Bidaia

				}
				// Cerrar el resultSet y PreparedStatement de la segunda consulta
				resultSet2.close();
				preparedStatement2.close();

			}

			// Cerrar el resultSet y PreparedStatement de la primera consulta
			resultSet1.close();
			preparedStatement1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Cerrar conexión siempre
		}

		return bidaiak;
	}

	public ArrayList<Lang_kopurua> langKopEduki() {
		ArrayList<Lang_kopurua> langKop = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM lang_kopurua";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String langKopKodea = resultSet.getString(1);
				String deskribapena = resultSet.getString(2);

				Lang_kopurua langKopurua = new Lang_kopurua(langKopKodea, deskribapena);
				langKop.add(langKopurua);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
		return langKop;
	}

	public ArrayList<Logela_motak> logelaMotaEduki() {
		ArrayList<Logela_motak> logelaMotaList = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM logela_motak";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String logelaKodea = resultSet.getString(1);
				String logelaDeskribapena = resultSet.getString(2);

				Logela_motak logelaMota = new Logela_motak(logelaKodea, logelaDeskribapena);
				logelaMotaList.add(logelaMota);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
		return logelaMotaList;
	}

	public ArrayList<Agentzia_Motak> agentziaMotaEduki() {
		ArrayList<Agentzia_Motak> agentziaMota = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM agentzia_motak";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String agentziaMKodea = resultSet.getString(1);
				String deskribapena = resultSet.getString(2);

				Agentzia_Motak agentziMota = new Agentzia_Motak(agentziaMKodea, deskribapena);
				agentziaMota.add(agentziMota);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
		return agentziaMota;
	}

	public ArrayList<Bidai_Motak> bidaiMotaEduki() {
		ArrayList<Bidai_Motak> bidaia = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM bidaia_motak";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String bidaiKodea = resultSet.getString(1);
				String deskribapena = resultSet.getString(2);

				Bidai_Motak bidaiM = new Bidai_Motak(bidaiKodea, deskribapena);

				bidaia.add(bidaiM);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
		return bidaia;
	}

	public ArrayList<Herrialde> herrialdeMotaEduki() {
		ArrayList<Herrialde> herrialde = new ArrayList<>();

		try {
			konexioa.konexioaIreki();

			sql = "SELECT * FROM herrialdeak";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				String herrialdeKodea = resultSet.getString(1);
				String deskribapena = resultSet.getString(2);

				Herrialde herrialdeList = new Herrialde(herrialdeKodea, deskribapena);

				herrialde.add(herrialdeList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
		return herrialde;
	}

	/*
	 * try { konexioa.konexioaIreki();
	 * 
	 * sql = ; PreparedStatement preparedStatement =
	 * konexioa.konektatuta.prepareStatement(sql); ResultSet resultSet =
	 * preparedStatement.executeQuery();
	 * 
	 * while (resultSet.next()) {
	 * 
	 * }
	 * 
	 * konexioa.konexioaItxi();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
	 * }
	 */

}
