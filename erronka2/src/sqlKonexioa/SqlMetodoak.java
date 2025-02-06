package sqlKonexioa;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import modelo.*;
import windowBuilder.Zerbitzuak;

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
	
	public void ezabatuBidaia(int bidaiKodea) {
	    try {
	        konexioa.konexioaIreki();  // Abre la conexión

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
	        e.printStackTrace();  // Muestra el error en consola
	    } finally {
	        konexioa.konexioaItxi(); // Cierra la conexión
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
		System.out.println("mierda!");
	    ArrayList<Bidaia> bidaiak = new ArrayList<>();
	    
	    try {
	        konexioa.konexioaIreki();
	        
	        // Primera consulta: Obtener los viajes
	        String sql1 = "SELECT * FROM bidaia WHERE agentzia_kodea = '" + cache.getAgentzia().getAgentziaKodea() + "'";
            System.out.println(sql1);
            
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
	            Bidaia bidai = new Bidaia(bidaiKodea, bidaiIzena, bidaiDeskribapena, bidaiEzBarne, bidaiHasiera, bidaiAmaiera, bidaiHerrialdeKod, bidaiMotaKod, bidaiAgentziaKod);
	            bidaiak.add(bidai);

	            // Segunda consulta: Obtener los servicios asociados a cada viaje
	            String sql2 ="SELECT *, Hegaldia.Zerbitzu_kodea AS Hegaldia_ID, ostatua.Zerbitzu_kodea AS Ostatua_ID, beste_batzuk.Zerbitzu_kodea AS Beste_Kodea"
					+ " FROM zerbitzuak" 
					+ " LEFT JOIN hegaldia ON zerbitzuak.Kodea = hegaldia.Zerbitzu_kodea" 
					+ " LEFT JOIN ostatua ON zerbitzuak.Kodea = ostatua.Zerbitzu_kodea" 
					+ " LEFT JOIN beste_batzuk ON zerbitzuak.Kodea = beste_batzuk.Zerbitzu_kodea"
	                + " WHERE Bidaiaren_kodea = '" + bidai.getBidaiKodea() + "'";
	            
	            //System.out.println(sql2);

		        PreparedStatement preparedStatement2 = konexioa.konektatuta.prepareStatement(sql2);
		        
		        ResultSet resultSet2 = preparedStatement2.executeQuery(sql2);


	            while (resultSet2.next()) {
	                Zerbitzu zerbitzuak = new Zerbitzu();
	                

	                if (resultSet2.getInt(24) != 0) {
	                    System.out.println("Servicio encontrado para el viaje " + resultSet2.getInt(24));
	                }
	                else if (resultSet2.getInt(25) != 0) {
	                	System.out.println("Servicio encontrado para el hospedaje " + resultSet2.getInt(25));
	                }
	                
	                else if (resultSet2.getInt(26) != 0) {
	                	System.out.println("Servicio encontrado para el beste " + resultSet2.getInt(26));
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
	
	public ArrayList<Logela_motak> logelaMotaEduki(){
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
