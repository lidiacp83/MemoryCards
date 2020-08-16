package es.studium.Juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {

	public String obtenerListaPuntuaciones() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/memorycard?autoReconnect=true&useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		String sentencia = "SELECT * FROM puntuaciones";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String s = null;
		String datosJugadores = "";

		// Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexión con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		// Preparar el statement
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				s = Integer.toString(rs.getInt("idjugador"));
				datosJugadores = datosJugadores + s + " - ";
				s = rs.getString("nombrejugador");
				datosJugadores = datosJugadores + s + " - ";
				s = Integer.toString(rs.getInt("puntuacion"));
				datosJugadores = datosJugadores + s + "\n";
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		return datosJugadores;
	}

	public int actualizarPuntuaciones(String idjugador, String nombre, String puntuacion) {
		int resultado = 0;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/memorycard?autoReconnect=true&useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		String sentencia = "UPDATE puntuaciones SET nombrejugador = '" + nombre + "', puntuacion = " + puntuacion
				+ " WHERE idjugador = " + idjugador;
		Connection connection = null;
		Statement statement = null;

		// Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexión con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		// Preparar el statement
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			resultado = statement.executeUpdate(sentencia);
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}
		return resultado;
	}

	public String[][] obtenerJugadores() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/memorycard?autoReconnect=true&useSSL=false";
		String login = "root";
		String password = "Studium2019;";
		String sentencia = "SELECT * FROM puntuaciones";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String[][] datosJugadores = new String[10][3];

		// Cargar el Driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		// Establecer la conexión con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		// Preparar el statement
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentencia);
			int i = 0;
			while (rs.next()) {
				datosJugadores[i][0] = Integer.toString(rs.getInt("idjugador"));
				datosJugadores[i][1] = rs.getString("nombrejugador");
				datosJugadores[i][2] = Integer.toString(rs.getInt("puntuacion"));
				i++;
			}
		} catch (SQLException e) {
			System.out.println("Error en la sentencia SQL:" + e.toString());
		}

		return datosJugadores;
	}

}
