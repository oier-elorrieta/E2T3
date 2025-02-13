package modelo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlKonexioa {

	String respuesta;
	public Connection konektatuta;

	public SqlKonexioa() {

	}

	public void konexioaIreki() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3307/db_bidaia_gestoreafroga";
			String user = "root";
			String pass = "";

			konektatuta = DriverManager.getConnection(url, user, pass);
			System.out.println("konektatuta");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void konexioaItxi() {
		try {
			konektatuta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}