package GestionHotel.modelo.repository.impl;

import GestionHotel.modelo.ExcepcionHotel;
import GestionHotel.modelo.ReservaVO;
import GestionHotel.modelo.repository.ReservaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ReservaRepositoryImpl implements ReservaRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public ReservaRepositoryImpl() {
    }

    public ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reservas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                Integer id_reserva = rs.getInt("id_reserva");
                Date fecha_llegada = rs.getDate("fecha_llegada");
                Date fecha_salida = rs.getDate("fecha_salida");
                Integer num_habitaciones = rs.getInt("num_habitaciones");
                String tipo_habitacion = rs.getString("tipo_habitacion");
                boolean fumador = rs.getBoolean("fumador");
                String tipo_alojamiento  = rs.getString("tipo_alojamiento");
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

    public ObservableList<ReservaVO> obtenerListaReservasCliente(String dni_cliente2) throws ExcepcionHotel {
        ObservableList<ReservaVO> reservas = FXCollections.observableArrayList();

        String sql = "SELECT * FROM reservas WHERE dni_cliente = ?";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dni_cliente2); // Evitar inyección SQL y asegurar la consulta.

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer id_reserva = rs.getInt("id_reserva");
                    Date fecha_llegada = rs.getDate("fecha_llegada");
                    Date fecha_salida = rs.getDate("fecha_salida");
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



    public void addReserva(ReservaVO m) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO reservas (id_reserva, fecha_llegada, fecha_salida, num_habitaciones, tipo_habitacion, fumador, tipo_alojamiento, dni_cliente) VALUES ('" + m.getIdReserva() + "','" + m.getFecha_llegada() + "','" + m.getFecha_salida() + "','" + m.getNum_habitaciones() + "','" + m.getTipo_habitacion() + "','" + m.isFumador() +  "','" + m.getTipo_alojamiento() + "','" + m.getDni_cliente() + "')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

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

    public void editReserva(ReservaVO clienteVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE reservas fecha_llegada = '%s', fecha_salida = '%s', num_habitaciones = '%s', tipo_habitacion = '%s', fumador = '%s', tipo_alojamiento = '%s', WHERE id_reserva = %d", clienteVO.getFecha_llegada(), clienteVO.getFecha_salida(), clienteVO.getNum_habitaciones(), clienteVO.getTipo_habitacion(), clienteVO.isFumador(), clienteVO.getTipo_alojamiento(), clienteVO.getIdReserva());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

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
