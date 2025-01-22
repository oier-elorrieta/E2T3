package sqlKonexioa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.util.Scanner;

public class sqlKonexioa {
	private static String url = "jdbc:mysql://localhost:3307/mendiak";
	private static String sql = "";
	private static Scanner sc = new Scanner(System.in);

	public static void kargatuBD() {
		try {
			// Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection(url, "root", "");

			// Preparamos la consulta
			Statement sentencia = conexion.createStatement();
			sql = "SELECT * FROM montes";
			ResultSet result = sentencia.executeQuery(sql);

			// Recorremos el resultado para visualizar cada fila
			while (result.next()) {

			}

			result.close(); // Cerrar ResultSet
			sentencia.close(); // Cerrar Statement
			conexion.close(); // Cerrar conexión

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void gordeBD() {
		try {
			// Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecemos la conexión con la BD
			Connection conexion = DriverManager.getConnection(url, "root", "");

			// Preparamos el Statement
			Statement sentencia = conexion.createStatement();

			// Eliminamos todos los datos existentes de la tabla (opcional)
			sql = "DELETE FROM montes";
			sentencia.executeUpdate(sql);

			// Recorremos los emails del array dentro de postontzi
			for (int i = 0; i <  ; i++) {
				if (montes != null) { // Verificamos que no sea nulo
					// Creamos la consulta SQL para insertar el email
					sql = "INSERT INTO montes VALUES ('" + montes.getIzena() + "', '" + montes.getProbintzia() + "', '"
							+ montes.getAltuera() + "', '" + montes.getKoordenadak() + "', '" + montes.getMendikate()
							+ "', '" + montes.getOhikoIbilbide() + "')";
					// Ejecutamos la consulta
					System.out.println(sql);
					sentencia.executeUpdate(sql);
				}
			}

			// Cerramos los recursos
			sentencia.close();
			conexion.close();

		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
