package gestioncom.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_commercial";
    private static final String USER = "root"; // Mets ton utilisateur MySQL
    private static final String PASSWORD = ""; // Mets ton mot de passe MySQL

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver MySQL
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
