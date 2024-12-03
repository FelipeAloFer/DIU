package GestionHotel.controller;

import GestionHotel.modelo.ReservaModelo;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class EstadisticasController {

    @FXML
    private AreaChart<String, Number> areaChart;// Usamos AreaChart en lugar de BarChart

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private ReservaModelo reservaModelo;
    private int[] meses;

    public void setReservaModelo(ReservaModelo reservaModelo) {
        this.reservaModelo = reservaModelo;
    }

    public void setMeses(String opcion) {
        switch (opcion) {
            case "Doble":
                this.meses = reservaModelo.contarReservasDobles();
                break;

            case "Individual":
                this.meses = reservaModelo.contarReservasIndividual();
                break;

            case "Junior Suite":
                this.meses = reservaModelo.contarReservasJuniorSuite();
                break;

            case "Suite":
                this.meses = reservaModelo.contarReservasSuite();
                break;

            default:
                throw new IllegalArgumentException("Opción no válida: " + opcion);
        }
        setDatosGrafico();
    }

    public void setDatosGrafico() {
        if (xAxis != null && yAxis != null) {
            // Limpiar datos previos
            areaChart.getData().clear();

            // Títulos de los ejes
            xAxis.setLabel("Mes");
            yAxis.setLabel("Habitaciones Alquiladas");

            // Array con los nombres de los meses
            String[] nombresMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

            // Crear una nueva serie de datos
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Habitaciones Alquiladas");

            // Rellenar los datos en la serie
            for (int i = 0; i < nombresMeses.length; i++) {
                series.getData().add(new XYChart.Data<>(nombresMeses[i], meses[i]));
            }

            // Agregar la serie al gráfico
            areaChart.getData().add(series);

            // Estilo CSS (recomendado usar un archivo externo)
            areaChart.lookupAll(".chart-area").forEach(node -> {
                node.setStyle("-fx-fill: rgba(76, 130, 175, 0.4);"); // Color azul suave con transparencia
            });

            // Agregar un borde estilizado al área del gráfico
            areaChart.lookupAll(".chart-series-line").forEach(node -> {
                node.setStyle("-fx-stroke: #4c82af; -fx-stroke-width: 2;"); // Línea azul con borde más grueso
            });

            // Ajustar el tamaño del gráfico
            areaChart.setPrefSize(800, 400);
        } else {
            System.err.println("Error: xAxis o yAxis no están inicializados.");
        }
    }
}
