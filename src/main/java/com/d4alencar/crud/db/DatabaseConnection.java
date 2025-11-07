package com.d4alencar.crud.db;

import java.sql.*;

public class DatabaseConnection {
  
  private static String url = "jdbc:postgresql://localhost:5432/db";
  private static String user = "user";
  private static String pass = "pass";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, pass);
  }
  
  public static void instanceTable() throws SQLException {
    String sql =  "CREATE TABLE IF NOT EXISTS books (" +
                  "id SERIAL PRIMARY KEY, " +
                  "title VARCHAR(100) NOT NULL, " +
                  "year INT NOT NULL, " +
                  "author VARCHAR(100) NOT NULL);";

    try (Connection conn = getConnection()) {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(sql);
    }
  }
}
