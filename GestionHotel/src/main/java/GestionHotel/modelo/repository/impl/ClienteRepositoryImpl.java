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

    // Obtiene la lista de clientes de la base de datos
    public ArrayList<ClienteVO> obtenerListaClientes() throws ExcepcionHotel {
        try {
            // Conexión a la base de datos
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM clientes";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            // Procesar los resultados de la consulta
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

            // Desconectar la base de datos
            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    // Añade un nuevo cliente a la base de datos
    public void addCliente(ClienteVO m) throws ExcepcionHotel {
        try {
            // Conexión a la base de datos
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

    // Elimina un cliente de la base de datos por su DNI
    public void deleteCliente(String dni) throws ExcepcionHotel {
        try {
            // Conexión a la base de datos
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM clientes WHERE dni = '%s'", dni);
            stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    // Edita los datos de un cliente en la base de datos
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
                stmt.setString(6, clienteVO.getDni());

                // Ejecutar la actualización
                int filasActualizadas = stmt.executeUpdate();

                // Verificar si se actualizó algún registro
                if (filasActualizadas > 0) {
                    System.out.println("Registro actualizado correctamente.");
                } else {
                    System.out.println("No se encontró ningún registro con ese DNI.");
                }
            }
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    // Obtiene el último ID registrado en la tabla de clientes
    public int lastId() throws ExcepcionHotel {
        int lastMonedaId = 0;

        try {
            // Conexión a la base de datos
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            // Ejecutar la consulta para obtener el último DNI
            for(ResultSet registro = comando.executeQuery("SELECT dni FROM clientes ORDER BY dni DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("codigo")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del ID");
        }
    }
}
