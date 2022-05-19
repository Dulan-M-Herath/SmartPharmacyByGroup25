package DB;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 18/05/2022
 * @time : 09:54 am
 * @project name : samart-pharmacy--onGoing--master
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

 private static DBConnection dbConnection;
 private Connection connection;

 private DBConnection() {

  try {
   Class.forName("com.mysql.jdbc.Driver");
   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartpharmacy","root","mysql");

  } catch (ClassNotFoundException | SQLException e) {
   e.printStackTrace();
  }
 }

 public static DBConnection getInstance() {
  return (dbConnection == null) ? dbConnection = new DBConnection() : dbConnection;
 }

 public Connection getConnection(){
  return connection;
 }
}
