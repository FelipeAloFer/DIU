package com.example.PracticaFS.controller;

import com.example.PracticaFS.Modelo.Articulo;
import com.example.PracticaFS.Modelo.CatalogoModelo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoArticuloController {
    private CatalogoModelo catalogoModelo;

    @FXML
    private TextField nombreA;
    @FXML
    private TextField descripcionA;
    @FXML
    private TextField precioA;
    @FXML
    private TextField stockA;
    @FXML
    private Label numeroAD;
    private Stage dialogStage;
    private Articulo articulo;
    private IntegerProperty numArticulos=new SimpleIntegerProperty();
    private boolean okClicked = false;
    public NuevoArticuloController() {
    }

    public void setNumeroAD() {
        this.numArticulos.bind(catalogoModelo.numeroArticulosProperty());
        numeroAD.setText(String.valueOf(numArticulos.get()));
        numArticulos.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                numeroAD.setText(String.valueOf(numArticulos.get()));
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setCatalogoModelo(CatalogoModelo catalogoModelo) {
        this.catalogoModelo = catalogoModelo;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
        nombreA.setText(articulo.getNombre());
        descripcionA.setText(articulo.getDescripcion());
        precioA.setText(String.valueOf(articulo.getPrecio()));
        stockA.setText(Integer.toString(articulo.getStock()));
    }
    public void handleAceptar(ActionEvent actionEvent) {
        if (isInputValid()) {
            articulo.setNombre(nombreA.getText());
            articulo.setDescripcion(descripcionA.getText());
            articulo.setPrecio(Float.parseFloat(precioA.getText()));
            articulo.setStock(Integer.parseInt(stockA.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreA.getText() == null || nombreA.getText().length() == 0) {
            errorMessage += "Nombre invalido\n";
        }
        if (descripcionA.getText() == null || descripcionA.getText().length() == 0) {
            errorMessage += "Descripcion invalida\n";
        }
        if (precioA.getText() == null || precioA.getText().length() == 0) {
            errorMessage += "Precio invalido\n";
        }else {
            try {
                Float.parseFloat(precioA.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Debe introducir un numero\n";
            }
        }

        if (stockA.getText() == null || stockA.getText().length() == 0) {
            errorMessage += "Stock invalido\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(stockA.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Debe introducir un numero\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Campos invalidos");
            alerta.setHeaderText("Introduzca correctamente los campos");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();
            return false;
        }
    }

    public void handleCancelar(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
