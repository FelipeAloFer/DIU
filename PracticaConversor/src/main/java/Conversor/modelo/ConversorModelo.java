package Conversor.modelo;

    import Modelo.ExcepcionMoneda;
    import Modelo.MonedaVO;
    import Modelo.repository.MonedaRepository;
    import javafx.scene.control.Alert;

    import java.util.ArrayList;

    public class ConversorModelo {
        ArrayList<MonedaVO> monedas;
        MonedaRepository monedaRepository;

        public ConversorModelo() {
        }

        public void setConversorModelo(MonedaRepository implementacion) throws ExcepcionMoneda {
            this.monedas = implementacion.ObtenerListaMonedas();
        }

        public Float conversor(Float euros) {
            if (monedas != null) {
                return (euros * monedas.get(0).getMultiplicador());
            } else {
                return null;
            }
        }
    }
