package com.example.agenda.model.repository;

import com.example.agenda.model.ExcepcionArticulo;
import com.example.agenda.model.ArticuloVO;
import java.util.ArrayList;

public interface ArticuloRepository {
    ArrayList<ArticuloVO> ObtenerListaArticulos() throws ExcepcionArticulo;

    void addArticulo(ArticuloVO var1) throws ExcepcionArticulo;

    void deleteArticulo(Integer var1) throws ExcepcionArticulo;

    void editArticulo(ArticuloVO var1) throws ExcepcionArticulo;

    int lastId() throws ExcepcionArticulo;

   }
