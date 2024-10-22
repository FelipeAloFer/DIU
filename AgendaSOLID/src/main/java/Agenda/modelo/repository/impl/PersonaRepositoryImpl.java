package Agenda.modelo.repository.impl;

import Agenda.modelo.ExcepcionAgenda;
import Agenda.modelo.PersonaVO;
import Agenda.modelo.repository.PersonaRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonaRepositoryImpl implements PersonaRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonaVO> personas;
    private PersonaVO persona;

    public PersonaRepositoryImpl() {
    }

    public ArrayList<PersonaVO> ObtenerListaPersonas() throws ExcepcionAgenda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM personas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String calle = rs.getString("calle");
                String ciudad = rs.getString("ciudad");
                int codigoPostal = rs.getInt("codigoPostal");
                String fechaNacimiento = rs.getString("fechaNacimiento");
                this.persona = new PersonaVO(codigo, nombre, apellido, calle, ciudad, codigoPostal, fechaNacimiento);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionAgenda("No se ha podido realizar la operación");
        }
    }

    public void addPersona(PersonaVO personaVO) throws ExcepcionAgenda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO personas (nombre, apellido, calle, ciudad, codigoPostal, fechaNacimiento) VALUES ('" + personaVO.getNombre() + "','" + personaVO.getApellido() + "','" + personaVO.getCalle() + "','" + personaVO.getCiudad() + "','" + personaVO.getCodigoPostal() + "','" + personaVO.getFechaNacimiento() + "')";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionAgenda("No se ha podido realizar la operación");
        }
    }

    public void deletePersona(Integer idMoneda) throws ExcepcionAgenda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM personas WHERE codigo = %d", idMoneda);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionAgenda("No se ha podido realizar la eliminación");
        }
    }

    public void editPersona(PersonaVO personaVO) throws ExcepcionAgenda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE personas SET nombre = '%s', apellido = '%s', calle = '%s', ciudad = '%s', codigoPostal = '%s', fechaNacimiento = '%s' WHERE codigo = %d", personaVO.getNombre(), personaVO.getApellido(), personaVO.getCalle(), personaVO.getCiudad(), personaVO.getCodigoPostal(), personaVO.getFechaNacimiento(), personaVO.getCodigoPersona());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionAgenda("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExcepcionAgenda {
        int lastPersonaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT codigo FROM personas ORDER BY codigo DESC LIMIT 1"); registro.next(); lastPersonaId = registro.getInt("codigo")) {
            }

            return lastPersonaId;
        } catch (SQLException var5) {
            throw new ExcepcionAgenda("No se ha podido realizar la busqueda del ID");
        }
    }
}

