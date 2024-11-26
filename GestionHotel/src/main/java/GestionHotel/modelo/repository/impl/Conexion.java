package GestionHotel.modelo.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Conexion() {
    }

    // Conecta a la base de datos y devuelve la conexión
    public Connection conectarBD() throws SQLException {
        try {
            // Establecer la conexión con la base de datos MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            // Registrar el driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return conn;
        } catch (SQLException var2) {
            // Captura y muestra los detalles de la SQLException
            SQLException ex = var2;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }

            throw new SQLException();
        } catch (Exception var3) {
            // Captura cualquier otro tipo de excepción y lanza una SQLException
            throw new SQLException();
        }
    }

    // Desconecta la conexión a la base de datos
    public void desconectarBD(Connection conn) {
        try {
            // Cierra la conexión
            conn.close();
        } catch (SQLException var3) {
            // Captura y muestra los detalles de la SQLException al cerrar la conexión
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }
    }
}
