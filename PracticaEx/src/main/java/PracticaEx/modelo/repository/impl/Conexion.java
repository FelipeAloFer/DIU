package PracticaEx.modelo.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // URL de conexión y credenciales
    private static final String URL = "jdbc:mysql://localhost:3306/catalogo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASSWORD = ""; // Contraseña de MySQL

    // Constructor
    public Conexion() {
    }

    // Método para conectar a la base de datos
    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException ex) {
            System.out.println("\n--- SQLException capturada ---\n");
            while (ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println();
            }
            throw ex; // Re-lanza la excepción para manejarla externamente si es necesario
        } catch (Exception ex) {
            System.out.println("Error inesperado: " + ex.getMessage());
            throw new SQLException("No se pudo conectar a la base de datos.", ex);
        }
    }

    // Método para desconectar la base de datos
    public void desconectarBD(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("\n--- SQLException capturada al cerrar la conexión ---\n");
                while (ex != null) {
                    System.out.println("Mensaje:   " + ex.getMessage());
                    System.out.println("SQLState:  " + ex.getSQLState());
                    System.out.println("ErrorCode: " + ex.getErrorCode());
                    ex = ex.getNextException();
                    System.out.println();
                }
            }
        }
    }
}

