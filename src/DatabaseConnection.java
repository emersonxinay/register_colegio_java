package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:postgresql://localhost:5432/colegio";
  private static final String USER = "postgres";
  private static final String PASSWORD = "emerson123";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void main(String[] args) {
    try {
      Connection conn = getConnection();
      if (conn != null) {
        System.out.println("Conexión exitosa a la base de datos.");
        conn.close(); // Cierra la conexión después de la prueba
      }
    } catch (SQLException e) {
      System.out.println("Error al conectar a la base de datos: " + e.getMessage());
    }
  }
}