package GestionHotel.modelo.repository.impl;

import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ReservaVO;
import GestionHotel.modelo.repository.ReservaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public ReservaRepositoryImpl() {
    }

    // Obtiene la lista completa de reservas desde la base de datos
    public ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer id_reserva = rs.getInt("id_reserva");

                // Convertir java.sql.Date a LocalDate
                java.sql.Date sqlFechaLlegada = rs.getDate("fecha_llegada");
                java.sql.Date sqlFechaSalida = rs.getDate("fecha_salida");

                LocalDate fecha_llegada = sqlFechaLlegada != null ? sqlFechaLlegada.toLocalDate() : null;
                LocalDate fecha_salida = sqlFechaSalida != null ? sqlFechaSalida.toLocalDate() : null;

                Integer num_habitaciones = rs.getInt("num_habitaciones");
                String tipo_habitacion = rs.getString("tipo_habitacion");
                boolean fumador = rs.getBoolean("fumador");
                String tipo_alojamiento = rs.getString("tipo_alojamiento");
                String dni_cliente = rs.getString("dni_cliente");

                this.reserva = new ReservaVO(id_reserva, fecha_llegada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, tipo_alojamiento, dni_cliente);
                this.reservas.add(this.reserva);
            }

            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    // Obtiene las reservas de un cliente específico
    public ObservableList<ReservaVO> obtenerListaReservasCliente(String dni_cliente2) throws ExcepcionHotel {
        ObservableList<ReservaVO> reservas = FXCollections.observableArrayList();

        String sql = "SELECT * FROM reservas WHERE dni_cliente = ?";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni_cliente2); // Evitar inyección SQL y asegurar la consulta.

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer id_reserva = rs.getInt("id_reserva");

                    // Conversión de java.sql.Date a LocalDate
                    java.sql.Date sqlFechaLlegada = rs.getDate("fecha_llegada");
                    java.sql.Date sqlFechaSalida = rs.getDate("fecha_salida");

                    LocalDate fecha_llegada = sqlFechaLlegada != null ? sqlFechaLlegada.toLocalDate() : null;
                    LocalDate fecha_salida = sqlFechaSalida != null ? sqlFechaSalida.toLocalDate() : null;

                    Integer num_habitaciones = rs.getInt("num_habitaciones");
                    String tipo_habitacion = rs.getString("tipo_habitacion");
                    boolean fumador = rs.getBoolean("fumador");
                    String tipo_alojamiento = rs.getString("tipo_alojamiento");
                    String dni_cliente = rs.getString("dni_cliente");

                    ReservaVO reserva = new ReservaVO(id_reserva, fecha_llegada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, tipo_alojamiento, dni_cliente);
                    reservas.add(reserva);
                }
            }

        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }

        return reservas;
    }

    // Agrega una nueva reserva a la base de datos
    public void addReserva(ReservaVO m) throws ExcepcionHotel {
        String sql = "INSERT INTO reservas (id_reserva, fecha_llegada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, tipo_alojamiento, dni_cliente) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getIdReserva());
            stmt.setDate(2, java.sql.Date.valueOf(m.getFecha_llegada())); // Conversión adecuada para fechas
            stmt.setDate(3, java.sql.Date.valueOf(m.getFecha_salida()));
            stmt.setInt(4, m.getNum_habitaciones());
            stmt.setString(5, m.getTipo_habitacion());
            stmt.setBoolean(6, m.isFumador()); // Manejo de boolean directamente
            stmt.setString(7, m.getTipo_alojamiento());
            stmt.setString(8, m.getDni_cliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la operación: " + e.getMessage());
        }
    }

    // Elimina una reserva de la base de datos por su id
    public void deleteReserva(int id_reserva) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM reservas WHERE id_reserva = %d", id_reserva);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    // Edita una reserva existente en la base de datos
    public void editReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        String sql = "UPDATE reservas SET fecha_llegada = ?, fecha_salida = ?, num_habitaciones = ?, tipo_habitacion = ?, fumador = ?, tipo_alojamiento = ?, dni_cliente = ? WHERE id_reserva = ?";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(reservaVO.getFecha_llegada())); // Manejo de fechas
            stmt.setDate(2, java.sql.Date.valueOf(reservaVO.getFecha_salida()));
            stmt.setInt(3, reservaVO.getNum_habitaciones());
            stmt.setString(4, reservaVO.getTipo_habitacion());
            stmt.setBoolean(5, reservaVO.isFumador()); // Manejo de booleanos
            stmt.setString(6, reservaVO.getTipo_alojamiento());
            stmt.setString(7, reservaVO.getDni_cliente());
            stmt.setInt(8, reservaVO.getIdReserva()); // `id_reserva` como string (ajustar si es otro tipo)
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la edición: " + e.getMessage());
        }
    }

    // Obtiene el último ID de reserva registrado
    public int lastId() throws ExcepcionHotel {
        int lastMonedaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT id_reserva FROM reservas ORDER BY id_reserva DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("codigo")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del ID");
        }
    }
}
