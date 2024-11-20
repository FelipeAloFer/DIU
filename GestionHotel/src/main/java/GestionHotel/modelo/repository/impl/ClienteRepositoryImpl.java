package GestionHotel.modelo.repository.impl;

import GestionHotel.modelo.ClienteVO;
import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.repository.ClienteRepository;

import java.sql.*;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    public ClienteRepositoryImpl() {
    }

    public ArrayList<ClienteVO> obtenerListaClientes() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM clientes";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");
                this.cliente = new ClienteVO(dni, nombre, apellidos, direccion, localidad, provincia);
                this.clientes.add(this.cliente);
            }

            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void addCliente(ClienteVO m) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO clientes (dni, nombre, apellidos, direccion, localidad, provincia) VALUES ('" + m.getDni() + "','" + m.getNombre() + "','" + m.getApellidos() + "','" + m.getDireccion() + "','" + m.getLocalidad() + "','" + m.getProvincia() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void deleteCliente(String dni) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM clientes WHERE dni = '%s'", dni);
            stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }


    public void editCliente(ClienteVO clienteVO) throws ExcepcionHotel {
        try (Connection conn = this.conexion.conectarBD()) {
            String sql = "UPDATE clientes SET nombre = ?, apellidos = ?, direccion = ?, localidad = ?, provincia = ? WHERE dni = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Asignar valores a los parámetros
                stmt.setString(1, clienteVO.getNombre());
                stmt.setString(2, clienteVO.getApellidos());
                stmt.setString(3, clienteVO.getDireccion());
                stmt.setString(4, clienteVO.getLocalidad());
                stmt.setString(5, clienteVO.getProvincia());
                stmt.setString(6, clienteVO.getDni()); // Cambiar a stmt.setInt(6, clienteVO.getDni()) si dni es un entero

                // Ejecutar la actualización
                int filasActualizadas = stmt.executeUpdate();

                // Mensaje para verificar el resultado
                if (filasActualizadas > 0) {
                    System.out.println("Registro actualizado correctamente.");
                } else {
                    System.out.println("No se encontró ningún registro con ese DNI.");
                }
            }
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la edición"); // Incluye la excepción original como causa
        }
    }


    public int lastId() throws ExcepcionHotel {
        int lastMonedaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT dni FROM clientes ORDER BY dni DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("codigo")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del ID");
        }
    }
}
