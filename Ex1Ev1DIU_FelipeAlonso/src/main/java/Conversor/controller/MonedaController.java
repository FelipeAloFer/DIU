package Conversor.controller;

import Conversor.modelo.MonedaModelo;
import Conversor.util.Moneda;
import Modelo.ExcepcionMoneda;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class MonedaController {
    MonedaModelo modelo;
    @FXML
    private TableView<Moneda> monedaTable;
    @FXML
    private TableColumn<Moneda, String> nombreColumna;

    @FXML
    protected Label monedaLabel;

    @FXML
    protected Label eurosLabel;

    @FXML
    protected TextField monedaField;

    @FXML
    protected TextField eurosField;

    private Main main;
    private MonedaModelo monedaModelo;

    public void setMonedaModelo(MonedaModelo monedaModelo) {
        this.monedaModelo = monedaModelo;
    }

    @FXML
    private void initialize() {
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        monedaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMonedaDetails(newValue));

    }

    private void showMonedaDetails(Moneda moneda) {
        if (moneda != null) {
            monedaLabel.setText(moneda.getNombre());
        } else {
            monedaLabel.setText("");
        }
    }

    @FXML
    public void handleConvertir() {
        if (monedaField.getText().isEmpty()) {
            Moneda selectedMoneda = monedaTable.getSelectionModel().getSelectedItem();
            float resultado = monedaModelo.conversorDesdeEuros(Float.valueOf(eurosField.getText()), selectedMoneda);
            monedaField.setText(String.valueOf(resultado));
        }
        if (eurosField.getText().isEmpty()) {
            Moneda selectedMoneda = monedaTable.getSelectionModel().getSelectedItem();
            float resultado = monedaModelo.conversorAEuros(Float.valueOf(monedaField.getText()), selectedMoneda);
            eurosField.setText(String.valueOf(resultado));

        }


    }

    @FXML
    public void handleEliminar() {
        int selectedIndex = monedaTable.getSelectionModel().getSelectedIndex();
        Moneda selectedArticulo = monedaTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            monedaTable.getItems().remove(selectedIndex);
            try {
                monedaModelo.eliminarMoneda(selectedArticulo.getCodigo());
            } catch (ExcepcionMoneda e) {
                throw  new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ningun articulo seleccionado");
            alert.setHeaderText("No hay articulo seleccionada");
            alert.setContentText("Porfavor selecciona un articulo en la tabla");
            alert.showAndWait();
        }
    }

    public void setMain(Main main) {
        this.main = main;

        monedaTable.setItems(main.getMonedasData());
    }
}
