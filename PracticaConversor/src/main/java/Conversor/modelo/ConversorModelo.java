package Conversor.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class ConversorModelo {
    ArrayList<MonedaVO> monedas;

    public ConversorModelo() {
    }

    public void setConversorModelo(MonedaRepository implementacion) throws ExcepcionMoneda {
        this.monedas = implementacion.ObtenerListaMonedas();
    }

    public Float conversor(Float euros) {
        return (euros * monedas.get(0).getMultiplicador());
    }
}
