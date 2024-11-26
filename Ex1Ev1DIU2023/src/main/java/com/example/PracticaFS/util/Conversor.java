package com.example.PracticaFS.util;

import Modelo.ArticuloVO;
import com.example.PracticaFS.Modelo.Articulo;

import java.util.ArrayList;

public class Conversor {
    public Articulo convertirArticuloVO(ArticuloVO articuloVO){
        Articulo articulo = new Articulo();
        articulo.setCodigo(articuloVO.getCodigo());
        articulo.setNombre(articuloVO.getNombre());
        articulo.setDescripcion(articuloVO.getDescripcion());
        articulo.setPrecio(articuloVO.getPrecio());
        articulo.setStock(articuloVO.getCantidad());
        return articulo;
    }

    public ArrayList<Articulo> lista(ArrayList<ArticuloVO> listaArticuloVO){
        ArrayList<Articulo> listaArticulo = new ArrayList<Articulo>();
        Articulo articulo = new Articulo();
        for(int i = 0; i< listaArticuloVO.size(); i++){
            articulo=convertirArticuloVO(listaArticuloVO.get(i));
            listaArticulo.add(i,articulo);
        }
        return listaArticulo;
    }
    public ArticuloVO convertirArticulo(Articulo articulo){
        ArticuloVO articuloVO = new ArticuloVO();
        articuloVO.setCodigo(articulo.getCodigo());
        articuloVO.setNombre(articulo.getNombre());
        articuloVO.setDescripcion(articulo.getDescripcion());
        articuloVO.setPrecio((float) articulo.getPrecio());
        articuloVO.setCantidad(articulo.getStock());
        return articuloVO;
    }
}
