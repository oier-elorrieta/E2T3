package sqlKonexioa;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.*;

public class SqlMetodoak {

	SqlKonexioa konexioa = new SqlKonexioa();

	private String sql = "";
	private Cache cache = new Cache();


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
				cache.setAgentzia(agentzia);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// cuando acaba el try catch da igual donde acabe siempre va a hacer el finally
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}

	public void erregistroEgin(String agentziaIzena, String kolorea, String langile, String agentziaMota, String logoa, String pasahitza) {
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
			        + agentzia.getMarkarenKolorea() + "', '" + agentzia.getIzena() + "', '"
			        + agentzia.getAgentziaMKod() + "', '" + agentzia.getPasahitza() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}
	
	public void bidaiaEgin(String bidaiIzena, String bidaiDeskribapena, String ezBarne, String bidaiHasiera, String bidaiAmaiera, String herrialdeKodea, String bidaiMKodea, int agentziaKodea) {
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
			        + bidaia.getIzena() + "', '" + bidaia.getDeskribapena() + "', '"
			        + bidaia.getEzBarne() + "', '" + bidaia.getBidaiHasiera() + "', '"
			        + bidaia.getBidaiAmaiera() + "', '" + bidaia.getHerrialdeKod() + "', '"
			        + bidaia.getBidaiaMKod() + "', '" + bidaia.getAgentziaKod() + "')";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			System.out.println(sql);
			preparedStatement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
		}
	}
	
	public ArrayList<Aireportu> aireportuaEduki(){
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
	
	public ArrayList<Airelinea> airelineaEduki(){
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

			sql = "SELECT * FROM bidaia";
			PreparedStatement preparedStatement = konexioa.konektatuta.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				int bidaiKodea = resultSet.getInt(1);
				String bidaiIzena = resultSet.getString(2);
				String bidaiDeskribapena = resultSet.getString(3);
				String bidaiEzBarne = resultSet.getString(4);
				Date bidaiHasiera = resultSet.getDate(5);
				Date bidaiAmaiera = resultSet.getDate(6);
				String bidaiHerrialdeKod = resultSet.getString(7);
				String bidaiMotaKod = resultSet.getString(8);
				int bidaiAgentziaKod =resultSet.getInt(9);
				
				Bidaia bidai = new Bidaia(bidaiKodea, bidaiIzena, bidaiDeskribapena, bidaiEzBarne, bidaiHasiera, bidaiAmaiera, bidaiHerrialdeKod, bidaiMotaKod, bidaiAgentziaKod);
				
				
				if(cache.getAgentzia().getAgentziaKodea() == bidai.getAgentziaKod()) {
					bidaiak.add(bidai);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			konexioa.konexioaItxi(); // Asegúrate de cerrar la conexión después de usarla
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
