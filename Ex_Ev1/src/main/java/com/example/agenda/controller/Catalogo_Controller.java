package com.example.agenda.controller;
import com.example.agenda.MainApp;
import com.example.agenda.model.ExcepcionArticulo;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.agenda.model.CatalogoModel;

import com.example.agenda.view.Articulo;

public class Catalogo_Controller {
    @FXML
    private TableView<Articulo> articuloTable;
    @FXML
    private TableColumn<Articulo, String> nombreColumn;
    @FXML
    private TableColumn<Articulo, String> precioColumn;

    @FXML
    private TextField unidadesField;

    @FXML
    private Label totalLabel;
    
    @FXML
    private Label nombreLabel;
    @FXML
    private Label descripcionLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private Label stockLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private CatalogoModel catalogoModel;

    public void setCatalogoModel(CatalogoModel catalogoModel) {
        this.catalogoModel = catalogoModel;
    }

    public Catalogo_Controller() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        precioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));


        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        articuloTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }


    private void showPersonDetails(Articulo articulo) {
        if (articulo != null) {
            // Fill the labels with info from the person object.
            nombreLabel.setText(articulo.getNombre());
            descripcionLabel.setText(articulo.getDescripcion());
            precioLabel.setText(String.valueOf(articulo.getPrecio()));
            stockLabel.setText(Integer.toString(articulo.getStock()));


        } else {
            // Person is null, remove all the text.
            nombreLabel.setText("");
            descripcionLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteArticulo() {
        int selectedIndex = articuloTable.getSelectionModel().getSelectedIndex();
        Articulo selectedArticulo = articuloTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            articuloTable.getItems().remove(selectedIndex);
            try {
                catalogoModel.eliminarArticulo(selectedArticulo.getId());
            } catch (ExcepcionArticulo e) {
                throw  new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay persona seleccionada");
            alert.setContentText("Porfavor selecciona una persona en la tabla");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleNewArticulo() {
        Articulo tempArticulo = new Articulo();
        boolean okClicked = mainApp.showPersonEditDialog(tempArticulo);

        if (okClicked && mainApp.getPersonData().size() < 50) {
            try {
                catalogoModel.añadirArticulo(tempArticulo);
                tempArticulo.setId(catalogoModel.obtenerID());

            } catch (ExcepcionArticulo e) {
                throw new RuntimeException(e);
            }
            mainApp.getPersonData().add(tempArticulo);
        }

//         else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Agenda completa");
//            alert.setHeaderText("No se pueden introducir más contactos");
//            alert.setContentText("Lo siento, tu agenda está completa.");
//            alert.showAndWait();
//        }

    }

    @FXML
    private void handleEditArticulo() {
        Articulo selectedArticulo = articuloTable.getSelectionModel().getSelectedItem();
        if (selectedArticulo != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedArticulo);

            try {
                catalogoModel.editarArticulo(selectedArticulo);
            } catch (ExcepcionArticulo e) {
                throw  new RuntimeException(e);
            }

            if (okClicked) {
                showPersonDetails(selectedArticulo);
            }
        } else {
            // Ninguna persona seleccionada
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No seleccionado");
            alert.setHeaderText("No hay articulo seleccionado");
            alert.setContentText("Por favor selecciona un articulo en la tabla.");
            alert.showAndWait();
        }
    }

    @FXML
    private void calcularTotal() {
        if (Integer.parseInt(stockLabel.getText()) >= (Integer.parseInt(unidadesField.getText()))) {
            totalLabel.setText(String.valueOf(Double.parseDouble(unidadesField.getText()) * Double.parseDouble(precioLabel.getText())));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Stock insuciciente");
            alert.setHeaderText("No hay stock suficiente");
            alert.setContentText("Por favor inserta una cantidad inferior al stock.");
            alert.showAndWait();
        }
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        articuloTable.setItems(mainApp.getPersonData());
    }
}