package com.d4alencar.crud.db;

import java.sql.*;


public class DatabaseConnection {
  
  private static String url = "jdbc:postgresql://localhost:5432/db";
  private static String user = "user";
  private static String pass = "pass";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, pass);
  }
}
