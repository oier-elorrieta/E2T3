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

	// instanciamos la clase de sql konexioa para que sea mas facil la conexión a la
	// base de datos
	SqlKonexioa konexioa = new SqlKonexioa();

	// iniciamos los metodos aquí arriba para no tener que iniciar todo en cada
	// metodo
	private String sql = "";
	// instanio caché para no tener que instanciarlo cada vez que lo uso
	private Cache cache = new Cache();

	// metodo para comparar el Log In en la base de datos

	public void loginKonparatu(String user, String pass) {

		try {

			// llamada al metodo de conexión con la BD

			konexioa.konexioaIreki();

			// consulta para sacar el usuario y contraseña

			sql = "SELECT * FROM agentzia WHERE izena = '" + user + "' and pasahitza = '" + pass + "'";

			// metemos lo de sql en el preparedStatement (un objeto que permite ejecutar
			// consultas SQL de manera segura)

			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);

			// ejecutamos la consulta sql y devuelve un resulset el resultset contiene el
			// resultado de la consulta, generalmente una tabla de datos devuelta por una
			// sentencia

			ResultSet resultSet = preparedStatement.executeQuery();

			// metemos todo lo que esté dentro del resultset en cada variable

			while (resultSet.next()) {

				int kodea = resultSet.getInt(1);
				String langileKopKod = resultSet.getString(2);
				String logoa = resultSet.getString(3);
				String markarenKolorea = resultSet.getString(4);
				String izena = resultSet.getString(5);
				String agentziaMKod = resultSet.getString(6);
				String pasahitza = resultSet.getString(7);

				// llamada al constructor para crear el objeto

				Agentzia agentzia = new Agentzia(kodea, langileKopKod, logoa, markarenKolorea, izena, agentziaMKod,
						pasahitza);

				// llamada a cache para que se setee la agencia temporalmente, esto hace que se
				// use lo de la agencia cuando está logeada
				
				cache.setAgentzia(agentzia);
				

				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// cuando acaba el try catch da igual donde acabe siempre va a hacer el finally
		} finally {
			konexioa.konexioaItxi(); // Asegura de cerrar la conexión después de usarla
		}
	}

	// metodo para hacer el register de la agentzia

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

			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	// Metodo para crear un servicio de ida

	public void zerbitzuJoanEgin(int bidaiKodea, String jatorrizkoAireportua, String helmugakoAireportua,
			String sJoanekoData, String hegaldiKodea, String aerolinea, float prezio, Time orduteguia, Time iraupena) {

		// instancio zerbitzu para setear las cosas

		Zerbitzu zerbitzuak = new Zerbitzu();

		// formateo la hora a la que está configurada en la base de datos

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// meto las cosas pasadas por parametros al objeto

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

			// conexión con la base de datos

			konexioa.konexioaIreki();

			// consulta para insertar los datos en la tabla de zerbitzuak

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.
			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				// Si no se puede obtener el ID generado automáticamente después de una
				// inserción en la base de datos,
				// se lanza una excepción con un mensaje de error.
				throw new SQLException("Error: Ezin izan da sortutako IDa lortu.");
			}

			// segunda consulta para insertar los datos en la tabla de hegaldia

			String sql2 = "INSERT INTO hegaldia VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getHegaldiKodea()
					+ "', '" + zerbitzuak.getHegaldiIrteraData() + "', '" + zerbitzuak.getHegaldiIrteeraOrdutegia()
					+ "', '" + zerbitzuak.getHegaldiBidaiarenIraupena() + "', '" + zerbitzuak.getHegaldiPrezioa()
					+ "', '" + zerbitzuak.getHegaldiJatorrizkoAireportua() + "', '"
					+ zerbitzuak.getHegaldiHelmugakoAireportua() + "', '" + zerbitzuak.getAirelinaKodea() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);
			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	// metodo para crear el servicio de ida y vuelta

	public void zerbitzuJoanEtorriEgin(int bidaiKodea, String jatorrizkoAireportua, String helmugakoAireportua,
			String sJoanekoData, String hegaldiKodea, String aerolinea, float prezio, Time orduteguia, Time iraupena,
			String jatorrizkoAireportuaE, String helmugakoAireportuaE, String sJoanekoDataE, String hegaldiKodeaE,
			String aerolineaE, Time orduteguiaE, Time iraupenaE) {

		// instancio zerbitzu para setear las cosas

		Zerbitzu zerbitzuak = new Zerbitzu();

		// formateo la hora a la que está configurada en la base de datos

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// setear todo lo que se pasa por parametros en el objeto

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
		zerbitzuak.setItzuleraOrdua(orduteguiaE);
		zerbitzuak.setBueltakoIraupena(iraupenaE);
		zerbitzuak.setBidaiKodea(bidaiKodea);

		int zerbitzuKodea = -1; // Variable para guardar el ID generado

		try {

			// conexión con la base de datos

			konexioa.konexioaIreki();

			// primera consulta para añadir el servicio en la tabla de zerbitzuak

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.

			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				// Si no se puede obtener el ID generado automáticamente después de una
				// inserción en la base de datos,
				// se lanza una excepción con un mensaje de error.
				throw new SQLException("Error: Ezin izan da sortutako IDa lortu.");
			}

			// segunda consulta para meter los datos en la tabla de hegaldia

			String sql2 = "INSERT INTO hegaldia VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getHegaldiKodea()
					+ "', '" + zerbitzuak.getHegaldiIrteraData() + "', '" + zerbitzuak.getHegaldiIrteeraOrdutegia()
					+ "', '" + zerbitzuak.getHegaldiBidaiarenIraupena() + "', '" + zerbitzuak.getHegaldiPrezioa()
					+ "', '" + zerbitzuak.getHegaldiJatorrizkoAireportua() + "', '"
					+ zerbitzuak.getHegaldiHelmugakoAireportua() + "', '" + zerbitzuak.getAirelinaKodea() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);

			preparedStatement2.executeUpdate();

			// tercera consulta para insertar las cosas en la tabla de joan_etorri

			String sql3 = "INSERT INTO joan_eta_etorri VALUES ('" + zerbitzuKodea + "', '"
					+ zerbitzuak.getHegaldiKodeaEtorri() + "', '" + zerbitzuak.getItzuleraOrdua() + "', '"
					+ zerbitzuak.getEtorriaEguna() + "', '" + zerbitzuak.getBueltakoIraupena() + "', '"
					+ zerbitzuak.getJoanJatorrizkoAireportua() + "', '" + zerbitzuak.getJoanHelmugakoAireportua()
					+ "', '" + zerbitzuak.getBueltakoAirelineaKodea() + "')";
			PreparedStatement preparedStatement3 = konexioa.konektatuta.prepareStatement(sql3);

			preparedStatement3.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	// metodo para crear el servicio de ostatua

	public void zerbitzuOstatuEgin(int bidaiKodea, String logelaMota, String hiria, float prezioO, String sSarreraDate,
			String sIrteeraDate) {

		// instancio zerbitzu para setear las cosas

		Zerbitzu zerbitzuak = new Zerbitzu();

		// formateo la hora a la que está configurada en la base de datos

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// setear todos los datos con los datos pasados por parametro

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

			// conexión con la base de datos

			konexioa.konexioaIreki();

			// primera consulta para insertar los datos en zerbitzuak

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.

			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				// Si no se puede obtener el ID generado automáticamente después de una
				// inserción en la base de datos,
				// se lanza una excepción con un mensaje de error.
				throw new SQLException("Error: Ezin izan da sortutako IDa lortu.");
			}

			// segunda consulta para meter los datos en la tabla de ostatua

			String sql2 = "INSERT INTO ostatua VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getOstatuPrezioa()
					+ "', '" + zerbitzuak.getOstatuSarreraEguna() + "', '" + zerbitzuak.getOstatuIrteraEguna() + "', '"
					+ zerbitzuak.getHiria() + "', '" + zerbitzuak.getLogelaMKodea() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);

			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	// metodo para hacer el servicio de jarduera

	public void zerbitzuJardueraEgin(int bidaiKodea, String jardueraIzena, String jardueraDeskribapena, float prezioJ,
			String sJardueraDate) {

		// instancio zerbitzu para setear las cosas

		Zerbitzu zerbitzuak = new Zerbitzu();

		// formateo la hora a la que está configurada en la base de datos

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// setear todos los datos pasados por parametros en el objeto

		zerbitzuak.setZerbitzuIzena(jardueraIzena);
		zerbitzuak.setBesteBatzukDeskribapena(jardueraDeskribapena);
		zerbitzuak.setBesteBatzukPrezioa(prezioJ);
		LocalDate jardueraDate = LocalDate.parse(sJardueraDate, formatter);
		zerbitzuak.setEgun(Date.valueOf(jardueraDate));
		zerbitzuak.setBidaiKodea(bidaiKodea);

		int zerbitzuKodea = -1; // Variable para guardar el ID generado

		try {

			// conexión con la base de datos

			konexioa.konexioaIreki();

			// primera consulta para insertar los datos en la tabla de zerbitzuak

			sql = "INSERT INTO zerbitzuak (izena, bidaiaren_kodea) VALUES ('" + zerbitzuak.getZerbitzuIzena() + "', '"
					+ zerbitzuak.getBidaiKodea() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS); // esto returnea la id que ha generado la base de datos, por que
														// está en autoincrement.

			preparedStatement.executeUpdate();

			// esto es para ver si ha generado una id o no.
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				zerbitzuKodea = generatedKeys.getInt(1);
			} else {
				// Si no se puede obtener el ID generado automáticamente después de una
				// inserción en la base de datos,
				// se lanza una excepción con un mensaje de error.
				throw new SQLException("Error: Ezin izan da sortutako IDa lortu.");
			}

			// segunda consulta para insertar los datos en la tabla de beste_batzuk

			String sql2 = "INSERT INTO beste_batzuk VALUES ('" + zerbitzuKodea + "', '" + zerbitzuak.getEgun() + "', '"
					+ zerbitzuak.getBesteBatzukDeskribapena() + "', '" + zerbitzuak.getBesteBatzukPrezioa() + "')";
			PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);

			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}

	}

	// metodo para crear el viaje

	public void bidaiaEgin(String bidaiIzena, String bidaiDeskribapena, String ezBarne, String bidaiHasiera,
			String bidaiAmaiera, String herrialdeKodea, String bidaiMKodea, int agentziaKodea) {

		// instancio bidaia para setear las cosas

		Bidaia bidaia = new Bidaia();

		// formateo la hora a la que está configurada en la base de datos

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// setear todos los datos con lo que se pasa por parametro

		bidaia.setIzena(bidaiIzena);
		bidaia.setDeskribapena(bidaiDeskribapena);
		bidaia.setEzBarne(ezBarne);
		// hago un parse para pasar de String a date de sql
		LocalDate dataHasiera = LocalDate.parse(bidaiHasiera, formatter);
		bidaia.setBidaiHasiera(Date.valueOf(dataHasiera));
		LocalDate dataAmaiera = LocalDate.parse(bidaiAmaiera, formatter);
		bidaia.setBidaiAmaiera(Date.valueOf(dataAmaiera));
		bidaia.setHerrialdeKod(herrialdeKodea);
		bidaia.setBidaiaMKod(bidaiMKodea);
		bidaia.setAgentziaKod(agentziaKodea);

		try {

			// conexión con la base de datos

			konexioa.konexioaIreki();

			// inserts de los datos en la tabla de bidaia

			sql = "INSERT INTO bidaia (bidaiaren_izena, deskribapena, Ez_barne, bidai_hasiera, bidai_amaiera, Herrialde_kodea, Bidaia_m_kodea, Agentzia_kodea) VALUES ('"
					+ bidaia.getIzena() + "', '" + bidaia.getDeskribapena() + "', '" + bidaia.getEzBarne() + "', '"
					+ bidaia.getBidaiHasiera() + "', '" + bidaia.getBidaiAmaiera() + "', '" + bidaia.getHerrialdeKod()
					+ "', '" + bidaia.getBidaiaMKod() + "', '" + bidaia.getAgentziaKod() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);

			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	// metodo para eliminar un viaje

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

	// metodo para eliminar un servicio

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

	// metodo para meter las cosas de la base de datos en los arraylist para los
	// comboBox de este metodo para abajo todos son iguales, hacen la misma funcion
	// (crear los objetos meterlos al array y luego usarlos para meter los datos al
	// ComboBox)

	public ArrayList<Aireportu> aireportuaEduki() {

		// creación del ArrayList

		ArrayList<Aireportu> aireportua = new ArrayList<>();

		try {

			// conexión de la base de datos

			konexioa.konexioaIreki();

			// consulta de la base de datos

			sql = "SELECT * FROM iata";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				// metemos lo del resultset en los String

				String aireportuKodea = resultSet.getString(1);
				String hiria = resultSet.getString(2);

				// creación del objeto iata

				Aireportu iata = new Aireportu(aireportuKodea, hiria);
				aireportua.add(iata); // añadimos el objeto al arrayList

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
		// returneamos el arrayList
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

	//metodo para meter los bidaias y zerbitzu en las tablas
	
	public ArrayList<Bidaia> bidaiakEduki() {
		
		//creación del arrayList bidaiak
		
		ArrayList<Bidaia> bidaiak = new ArrayList<>();

		try {
			
			//conexión con la base de datos
			
			konexioa.konexioaIreki();

			// Primera consulta: Obtener los viajes
			String sql1 = "SELECT * FROM bidaia WHERE agentzia_kodea = '" + cache.getAgentzia().getAgentziaKodea()
					+ "'";

			PreparedStatement preparedStatement1 = konexioa.konektatuta.prepareStatement(sql1);

			ResultSet resultSet1 = preparedStatement1.executeQuery(sql1);
			
			while (resultSet1.next()) {
			
				//meter todo lo del resultset en variables
				
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
					//dependiendo en la posición que esté la id en la base de datos entra en un sitio u otro
					if (resultSet2.getInt(4) != 0 && resultSet2.getInt(4) != resultSet2.getInt(13)) {

						//meter todo lo del resultset en variables
						
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

						//crear el objeto zerbitzuak
						
						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodeaH, zerbitzuIzenaH, bidaiKodeaH, hegaldiKodea,
								hegaldiIrteraData, hegaldiIrteeraOrdutegia, hegaldiBidaiarenIraupena, hegaldiPrezioa,
								hegaldiJatorrizkoAireportua, hegaldiHelmugakoAireportua, airelinaKodea);

						//llamar al metodo para meterlo en el arrayList que está en el objeto bidaiak
						
						bidai.gehituZerbitzuak(zerbitzuak);

					} else if (resultSet2.getInt(13) != 0 && resultSet2.getInt(13) == resultSet2.getInt(4)) {

						//meter todo lo del resultset en variables
						
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

						//crear el objeto zerbitzuak
						
						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodeaHE, zerbitzuIzenaH, bidaiKodeaHE, hegaldiKodea,
								hegaldiIrteraData, hegaldiIrteeraOrdutegia, hegaldiBidaiarenIraupena, hegaldiPrezioa,
								hegaldiJatorrizkoAireportua, hegaldiHelmugakoAireportua, airelinaKodea,
								hegaldiKodeaEtorri, itzuleraOrdua, etorriaEguna, bueltakoIraupena,
								joanJatorrizkoAireportua, joanHelmugakoAireportua, bueltakoAirelineaKodea);

						//llamar al metodo para meterlo en el arrayList que está en el objeto bidaiak
						
						bidai.gehituZerbitzuak(zerbitzuak);
					}

					else if (resultSet2.getInt(21) != 0) {

						//meter todo lo del resultset en variables
						
						int zerbitzuKodea = resultSet2.getInt(21);
						String zerbitzuIzena = resultSet2.getString(2);
						int bidaiKodeaO = resultSet2.getInt(3);
						float ostatuPrezioa = resultSet2.getFloat(22);
						Date ostatuSarreraEguna = resultSet2.getDate(23);
						Date ostatuIrteraEguna = resultSet2.getDate(24);
						String hiria = resultSet2.getString(25);
						String logelaMKodea = resultSet2.getString(26);

						//crear el objeto zerbitzuak
						
						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodea, zerbitzuIzena, bidaiKodeaO, ostatuPrezioa,
								ostatuSarreraEguna, ostatuIrteraEguna, hiria, logelaMKodea);

						//llamar al metodo para meterlo en el arrayList que está en el objeto bidaiak
						
						bidai.gehituZerbitzuak(zerbitzuak);

					}

					else if (resultSet2.getInt(27) != 0) {

						//meter todo lo del resultset en variables
						
						int zerbitzuKodea = resultSet2.getInt(27);
						String zerbitzuIzena = resultSet2.getString(2);
						int bidaiKodeaB = resultSet2.getInt(3);
						Date egun = resultSet2.getDate(28);
						String besteBatzukDeskribapena = resultSet2.getString(29);
						float besteBatzukPrezioa = resultSet2.getFloat(30);

						//crear el objeto zerbitzuak
						
						Zerbitzu zerbitzuak = new Zerbitzu(zerbitzuKodea, zerbitzuIzena, bidaiKodeaB, egun,
								besteBatzukDeskribapena, besteBatzukPrezioa);

						//llamar al metodo para meterlo en el arrayList que está en el objeto bidaiak
						
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

		//returnear el arrayList de bidaiak
		
		return bidaiak;
	}

}
