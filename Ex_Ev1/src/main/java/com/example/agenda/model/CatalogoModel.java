package com.example.agenda.model;
import com.example.agenda.model.repository.ArticuloRepository;


import com.example.agenda.view.Articulo;
import com.example.agenda.util.ArticuloUtil;

import java.util.ArrayList;

public class CatalogoModel {

    private int totalProductos = 50;

    private ArticuloRepository articuloRepository; //Esta es la dependencia de ConversorModelo con MonedaRepository
    ArrayList<ArticuloVO> articulos = new ArrayList<>();

    public void setArticuloRepository(ArticuloRepository articuloRepository)  throws ExcepcionArticulo {
        this.articuloRepository = articuloRepository;
    }

    public ArrayList<Articulo> setArticulos() throws ExcepcionArticulo {
        articulos= articuloRepository.ObtenerListaArticulos();
        totalProductos= totalProductos - articulos.size();
        return ArticuloUtil.parseToArticulo(articulos);
    }

    public void editarArticulo(Articulo articulo) throws ExcepcionArticulo {
        articuloRepository.editArticulo(ArticuloUtil.parseToArticuloVO(articulo));
    }

    public void añadirArticulo(Articulo articulo) throws ExcepcionArticulo {
        articuloRepository.addArticulo(ArticuloUtil.parseToArticuloVO(articulo));
        decrementarTotalProductos();
    }

    public void eliminarArticulo(int codigo) throws ExcepcionArticulo {
        articuloRepository.deleteArticulo(codigo);
//        incrementarTotalProductos();
    }

    public int obtenerID () throws ExcepcionArticulo {
        return articuloRepository.lastId();
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    private void decrementarTotalProductos() {
        if (totalProductos > 0) {
            totalProductos--;
        }
    }

    private void incrementarTotalProductos() {
        totalProductos++;
    }

}
