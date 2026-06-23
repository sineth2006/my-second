package lk.ijse.project_ii.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sineth
 */
public class DBConnection {
    private final String DB_URL="jdbc:mysql://localhost:3306/student_management";
    private final String DB_USER="root";
    private final String DB_PASSWORD="ett25304";
    private Connection conn;
    private static DBConnection dbc;
    
    private DBConnection() throws SQLException  {
         
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public static DBConnection getInstance() throws SQLException {
        return (dbc==null) ? dbc = new DBConnection() : dbc;
    }
    
    public Connection getConnection() {
        System.out.println("connection ok");
        return conn;
    }
}
