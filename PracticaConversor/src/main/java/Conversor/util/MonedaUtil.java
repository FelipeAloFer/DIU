package Conversor.util;

import Conversor.modelo.Moneda;
import Modelo.MonedaVO;

import java.util.ArrayList;

public class MonedaUtil {
    static ArrayList<Moneda> monedas1 = new ArrayList<>();

    public static ArrayList<Moneda> convertir(ArrayList<MonedaVO> monedas) {
        for (MonedaVO moneda : monedas) {
            monedas1.add(new Moneda(monedas.get(0).getNombre(), monedas.get(0).getMultiplicador()));
        }
        return monedas1;
    }
}
