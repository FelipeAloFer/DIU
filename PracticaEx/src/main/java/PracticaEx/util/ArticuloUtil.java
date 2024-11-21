package PracticaEx.util;

import PracticaEx.modelo.ArticuloVO;

import java.util.ArrayList;

public class ArticuloUtil {

    public static ArrayList<Articulo> parseToArticulo(ArrayList<ArticuloVO> lista) {
        if (lista == null) {
            return null;
        } else {
            ArrayList<Articulo> personas = new ArrayList<>();
            for (ArticuloVO articuloVO : lista) {
                personas.add(new Articulo(articuloVO.getNombre(), articuloVO.getDescripcion(), articuloVO.getPrecio(), articuloVO.getStock(), articuloVO.getId()));
            }
            return personas;
        }
    }

    public static ArticuloVO parseToArticuloVO(Articulo articulo) {
              ArticuloVO articuloVO= new ArticuloVO(articulo.getId(), articulo.getNombre(), articulo.getDescripcion(), articulo.getPrecio(), articulo.getStock());
            return articuloVO;
        }
    }


