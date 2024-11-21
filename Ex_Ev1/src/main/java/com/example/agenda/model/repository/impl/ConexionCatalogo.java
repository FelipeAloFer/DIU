package com.example.agenda.model.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionCatalogo {

    // URL de conexión y credenciales
    private static final String URL = "jdbc:mysql://localhost:3306/catalogo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root"; // Usuario de MySQL
    private static final String PASSWORD = ""; // Contraseña de MySQL

    // Constructor
    public ConexionCatalogo() {
    }

    // Método para conectar a la base de datos
    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida exitosamente.");
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
                System.out.println("Conexión cerrada exitosamente.");
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

