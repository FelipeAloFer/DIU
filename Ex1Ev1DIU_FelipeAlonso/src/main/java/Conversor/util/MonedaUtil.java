package Conversor.util;


import Modelo.MonedaVO;

import java.util.ArrayList;

public class MonedaUtil {

    public static ArrayList<Moneda> convertirToMoneda(ArrayList<MonedaVO> lista) {
        if (lista == null) {
            return null;
        } else {
            ArrayList<Moneda> monedas = new ArrayList<>();
            for (MonedaVO monedaVO : lista) {
                monedas.add(new Moneda(monedaVO.getCodigo(), monedaVO.getNombre(), monedaVO.getMultiplicador()));
            }
            return monedas;
        }
    }

    public static MonedaVO convertirToMonedaVO(Moneda moneda) {
        MonedaVO monedaVO = new MonedaVO(moneda.getNombre(), moneda.getMultiplicador(), moneda.getCodigo());
        return monedaVO;
    }
}

