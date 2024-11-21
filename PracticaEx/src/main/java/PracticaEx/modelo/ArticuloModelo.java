package PracticaEx.modelo;

import PracticaEx.modelo.repository.ArticuloRepository;
import PracticaEx.util.Articulo;
import PracticaEx.util.ArticuloUtil;

import java.util.ArrayList;

public class ArticuloModelo {

    private int totalProductos = 50;

    private ArticuloRepository articuloRepository;
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

}
