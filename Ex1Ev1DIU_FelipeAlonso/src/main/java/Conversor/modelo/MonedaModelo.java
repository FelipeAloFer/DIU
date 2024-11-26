package Conversor.modelo;

import Conversor.util.Moneda;
import Conversor.util.MonedaUtil;
import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;

import java.util.ArrayList;

public class MonedaModelo {
    private MonedaRepository monedaRepository;
    ArrayList<MonedaVO> monedasVO = new ArrayList<>();

    public MonedaModelo() {
    }

    public void setMonedaRepository(MonedaRepository monedaRepository) throws ExcepcionMoneda {
        this.monedaRepository = monedaRepository;
    }

    public ArrayList<Moneda> setMonedas() throws ExcepcionMoneda {
        monedasVO = monedaRepository.ObtenerListaMonedas();
        return MonedaUtil.convertirToMoneda(monedasVO);
    }

    public Float conversorDesdeEuros(Float euros, Moneda moneda) {
        if (euros != null) {
            return (euros * moneda.getMultiplicador());
        } else {
            return null;
        }
    }

    public Float conversorAEuros(Float monedas, Moneda moneda) {
        if (monedas != null) {
            float nuevoMultiplicador = (2 - moneda.getMultiplicador());
            float resultado = monedas * nuevoMultiplicador;
            return (resultado);
        } else {
            return null;
        }
    }

    public void eliminarMoneda(int codigo) throws ExcepcionMoneda {
        monedaRepository.deleteMoneda(codigo);
    }
}
