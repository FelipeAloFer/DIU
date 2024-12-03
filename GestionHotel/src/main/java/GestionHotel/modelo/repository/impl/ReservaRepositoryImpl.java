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
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_reserva);
            stmt.executeUpdate();
        } catch (SQLException e) {
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
        String sql = "SELECT id_reserva FROM reservas ORDER BY id_reserva DESC LIMIT 1";
        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("id_reserva");
            }
            return 0; // Retorna 0 si no hay registros.
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se pudo obtener el último ID");
        }
    }

    private int contarPorTipoDeHabitacion(String tipoDeHabitacion) throws ExcepcionHotel {
        int total = 0;
        String consulta = "SELECT COUNT(*) AS total " +
                "FROM reservas " +
                "WHERE tipo_habitacion = ? AND fecha_Llegada <= CURRENT_DATE AND fecha_Salida >= CURRENT_DATE";

        try (Connection conexion = this.conexion.conectarBD();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setString(1, tipoDeHabitacion);

            try (ResultSet resultados = sentencia.executeQuery()) {
                if (resultados.next()) {
                    total = resultados.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public int obtenerHabitacionesDobles() throws ExcepcionHotel {
        return contarPorTipoDeHabitacion("Doble");
    }

    @Override
    public int obtenerHabitacionesIndividuales() throws ExcepcionHotel {
        return contarPorTipoDeHabitacion("Doble_de_uso_individual");
    }

    @Override
    public int obtenerHabitacionesJunior() throws ExcepcionHotel {
        return contarPorTipoDeHabitacion("Junior_suite");
    }

    @Override
    public int obtenerHabitacionesSuite() throws ExcepcionHotel {
        return contarPorTipoDeHabitacion("Suite");
    }

    @Override
    public int[] contarMeses(String tipoHabitacion) throws ExcepcionHotel {
        int[] cuentaPorMes = new int[12];

        String sql = "SELECT m.mes, COUNT(r.id_reserva) AS total " +
                "FROM ( " +
                "    SELECT 1 AS mes UNION ALL " +
                "    SELECT 2 UNION ALL " +
                "    SELECT 3 UNION ALL " +
                "    SELECT 4 UNION ALL " +
                "    SELECT 5 UNION ALL " +
                "    SELECT 6 UNION ALL " +
                "    SELECT 7 UNION ALL " +
                "    SELECT 8 UNION ALL " +
                "    SELECT 9 UNION ALL " +
                "    SELECT 10 UNION ALL " +
                "    SELECT 11 UNION ALL " +
                "    SELECT 12 " +
                ") AS m " +
                "LEFT JOIN reservas r ON MONTH(r.fecha_llegada) = m.mes AND r.tipo_habitacion = ? " +
                "GROUP BY m.mes " +
                "ORDER BY m.mes";


        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoHabitacion);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int mes = rs.getInt("mes") - 1;
                    cuentaPorMes[mes] = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionHotel("Error al contar las reservas por mes.");
        }

        return cuentaPorMes;
    }

}
