package com.example.agenda.controller;
import com.example.agenda.model.CatalogoModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.example.agenda.view.Articulo;
import javafx.scene.control.Alert;


/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class Nuevo_Articulo_Controller {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField precioField;
    @FXML
    private TextField stockField;
    @FXML
    private Label productosDisponiblesLabel;
    @FXML
    private ProgressBar barra;

    CatalogoModel catalogoModel;
    private Stage dialogStage;
    private Articulo articulo;
    private boolean okClicked = false;
    private IntegerProperty valorControlador = new SimpleIntegerProperty();


    @FXML
    private void initialize() {
        barra.progressProperty().bind(valorControlador.divide(50.0));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
        nombreField.setText(articulo.getNombre());
        descripcionField.setText(articulo.getDescripcion());
        precioField.setText(String.valueOf(articulo.getPrecio()));
        stockField.setText(Integer.toString(articulo.getStock()));

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            articulo.setNombre(nombreField.getText());
            articulo.setDescripcion(descripcionField.getText());
            articulo.setPrecio(Double.parseDouble(precioField.getText()));
            articulo.setStock(Integer.parseInt(stockField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre inválido!\n";
        }
        if (descripcionField.getText() == null || descripcionField.getText().length() == 0) {
            errorMessage += "Apellido inválido!\n";
        }
        if (precioField.getText() == null || precioField.getText().length() == 0) {
            errorMessage += "Calle inválida!\n";
        }
        if (stockField.getText() == null || stockField.getText().length() == 0) {
            errorMessage += "Calle inválida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos inválidos");
            alert.setHeaderText("Porfavor introduce campos correctos");
            alert.setContentText("Campos inválidos");
            alert.showAndWait();
            return false;
        }
    }

    public void setCatalogoModel(CatalogoModel catalogoModel) {
        this.catalogoModel = catalogoModel;
    }
    public void setTotalProductos(){
        productosDisponiblesLabel.setText(String.valueOf(catalogoModel.getTotalProductos()));
    }

    public void bindVariable(IntegerProperty valorMain) {
        valorControlador.bind(valorMain); // Vincular unidireccionalmente
        productosDisponiblesLabel.textProperty().bind(valorControlador.asString());
        barra.progressProperty().bind(valorControlador.divide(50.0)); // Normalizar a rango [0.0, 1.0]
    }


}