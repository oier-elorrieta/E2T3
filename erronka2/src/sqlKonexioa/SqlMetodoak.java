package sqlKonexioa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.*;

public class SqlMetodoak {

	SqlKonexioa konexioa = new SqlKonexioa();

	private String sql = "";

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

				Agentzia agentzia = new Agentzia(kodea, langileKopKod, logoa, markarenKolorea, izena, agentziaMKod, pasahitza);
				Cache cache = new Cache();
				cache.setAgentzia(agentzia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// cuando acaba el try catch da igual donde acabe siempre va a hacer el finally
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	public void erregistroEgin() {
		Agentzia agentzia = new Agentzia();

		try {
			konexioa.konexioaIreki();
			
			

			sql = "INSERT INTO (langile_kopuru_kodea, logoa, markaren_kolorea, izena, agentzia_m_kodea, pasahitza) agentzia VALUES ('"
					+ agentzia.getLangileKopKod() + "', '" + agentzia.getLogoa() + "', '"
					+ agentzia.getMarkarenKolorea() + "', '" + agentzia.getIzena() + "', '" + agentzia.getAgentziaMKod()
					+ "', '" + agentzia.getPasahitza() + "',)";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
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
