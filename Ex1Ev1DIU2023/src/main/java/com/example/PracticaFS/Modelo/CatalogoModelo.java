package com.example.PracticaFS.Modelo;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import com.example.PracticaFS.controller.TotalInterface;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class CatalogoModelo {

    ArticuloRepository articuloRepository;
    TotalInterface totalInterface;

    IntegerProperty numeroArticulos=new SimpleIntegerProperty();
    public void setNumeroArticulos(Integer nP){
        this.numeroArticulos.set(nP);
    }
    public  void decNumeroArticulos(){
        this.numeroArticulos.set(numeroArticulos.get()-1);
    }
    public  void incNumeroArticulos() {
        this.numeroArticulos.set(numeroArticulos.get()+1);
    }

    public IntegerProperty numeroArticulosProperty() {
        return numeroArticulos;
    }
    public CatalogoModelo() {
    }
    public ArticuloVO consulta(int var1) throws ExcepcionArticulo {
        return articuloRepository.consulta(var1);
    }

    public  void addArticulo(ArticuloVO var1) throws ExcepcionArticulo{
        articuloRepository.addArticulo(var1);
    }
    public ArrayList<ArticuloVO> obternerListaArticulos() throws ExcepcionArticulo{
        return articuloRepository.obternerListaArticulos();
    }
    public float total(Integer unidades, Float precio){
        return totalInterface.total(unidades,precio);
    }
    public void setArticuloRepository(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public void setTotalInterface(TotalInterface totalInterface) {
        this.totalInterface = totalInterface;
    }
}
