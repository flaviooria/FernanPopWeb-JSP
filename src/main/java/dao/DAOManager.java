package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utilidades.FicherosConverter;

public class DAOManager {
  private Connection conn;
  private final String URL;
  private final String USER;
  private final String PASS;
  private static DAOManager singleton;

  private DAOManager() {
    conn = null;
    URL = FicherosConverter.obtenerPropertieDataBase("url");
    USER = FicherosConverter.obtenerPropertieDataBase("user");
    PASS = FicherosConverter.obtenerPropertieDataBase("pass");
  }

  public static DAOManager getSingletonInstance() {
    if (singleton == null) {
      singleton = new DAOManager();
      return singleton;
    } else return null;
  }

  public Connection getConn() {
    return conn;
  }

  public void open() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection(URL,USER,PASS);
  }

  public void close() throws SQLException {
    if (conn != null) conn.close();
  }
}
