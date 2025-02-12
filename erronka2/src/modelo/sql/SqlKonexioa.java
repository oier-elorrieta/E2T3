package modelo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlKonexioa {

	String respuesta;
	public Connection konektatuta;

	public SqlKonexioa() {

	}

	//Metodo para crear la konexion con la base de datos
	
	public void konexioaIreki() {
		try {
			
			//driver para poder conectarse con la base de datos
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			//url de la base de datos usuario y contraseña
			
			String url = "jdbc:mysql://localhost:3307/db_bidaia_gestorea";
			String user = "root";
			String pass = "";

			//pilla la url el usuario y contraseña y intenta logearse en la base de datos
			
			konektatuta = DriverManager.getConnection(url, user, pass);
			System.out.println("konektatuta");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//metodo para cerrar la conexión de la base de datos

	public void konexioaItxi() {
		try {
			System.out.println("deskonektatuta");
			konektatuta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}