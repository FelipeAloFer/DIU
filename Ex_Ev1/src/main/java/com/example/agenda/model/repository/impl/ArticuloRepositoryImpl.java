package com.example.agenda.model.repository.impl;
import com.example.agenda.model.ExcepcionArticulo;
import com.example.agenda.model.ArticuloVO;
import com.example.agenda.model.repository.ArticuloRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticuloRepositoryImpl implements ArticuloRepository {
    private final ConexionCatalogo conexion = new ConexionCatalogo();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ArticuloVO> articulos;
    private ArticuloVO articulo;

    public ArticuloRepositoryImpl() {
    }

    public ArrayList<ArticuloVO> ObtenerListaArticulos() throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD();
            this.articulos = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM articulo";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                Integer codigo = rs.getInt("id");
                String Nombre = rs.getString("nombre");
                String Descripcion = rs.getString("descripcion");
                Double Precio = rs.getDouble("precio");
                Integer Stock = rs.getInt("stock");
                this.articulo = new ArticuloVO(codigo, Nombre, Descripcion, Precio, Stock);
                this.articulos.add(this.articulo);
            }

            this.conexion.desconectarBD(conn);
            return this.articulos;
        } catch (SQLException var6) {
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }

    public void addArticulo(ArticuloVO p) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO articulo (nombre, descripcion, precio, stock) VALUES ('" + p.getNombre()  + "','"+ p.getDescripcion() + "','"+ p.getPrecio() + "','"+ p.getStock() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }

    public void deleteArticulo(Integer idArticulo) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM articulo WHERE id = %d", idArticulo);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionArticulo("No se ha podido realizar la eliminación");
        }
    }

    public void editArticulo(ArticuloVO articuloVO) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE articulo SET nombre = '%s', descripcion = '%s', precio = '%s', stock = '%s WHERE id = %d", articuloVO.getNombre(), articuloVO.getDescripcion(), articuloVO.getPrecio(), articuloVO.getStock(), articuloVO.getId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionArticulo("No se ha podido relaizar la edición");
        }
    }

    public int lastId() throws ExcepcionArticulo {
        int lastPersonaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT id FROM articulo ORDER BY id DESC LIMIT 1"); registro.next(); lastPersonaId = registro.getInt("id")) {
            }

            return lastPersonaId;
        } catch (SQLException var5) {
            throw new ExcepcionArticulo("No se ha podido realizar la busqueda del ID");
        }
    }

}
