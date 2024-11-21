package PracticaEx.controller;


import PracticaEx.modelo.ArticuloModelo;
import PracticaEx.modelo.ExcepcionArticulo;
import PracticaEx.util.Articulo;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ArticuloController {
    @FXML
    private TableView<Articulo> articuloTable;
    @FXML
    private TableColumn<Articulo, String> nombreColumna;
    @FXML
    private TableColumn<Articulo, String> precioColumna;

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

    private Main main;
    private ArticuloModelo articuloModelo;

    public void setCatalogoModel(ArticuloModelo articuloModelo) {
        this.articuloModelo = articuloModelo;
    }

    public ArticuloController() {
    }

    @FXML
    private void initialize() {
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        precioColumna.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));


        showPersonDetails(null);

        articuloTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }


    private void showPersonDetails(Articulo articulo) {
        if (articulo != null) {
            nombreLabel.setText(articulo.getNombre());
            descripcionLabel.setText(articulo.getDescripcion());
            precioLabel.setText(String.valueOf(articulo.getPrecio()));
            stockLabel.setText(Integer.toString(articulo.getStock()));


        } else {
            nombreLabel.setText("");
            descripcionLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
        }
    }

    @FXML
    private void handleNewArticulo() {
        Articulo tempArticulo = new Articulo();
        boolean okClicked = main.showPersonEditDialog(tempArticulo);

        if (okClicked && main.getPersonData().size() < 50) {
            try {
                articuloModelo.añadirArticulo(tempArticulo);
                tempArticulo.setId(articuloModelo.obtenerID());

            } catch (ExcepcionArticulo e) {
                throw new RuntimeException(e);
            }
            main.getPersonData().add(tempArticulo);
        }
//        else {
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
            boolean okClicked = main.showPersonEditDialog(selectedArticulo);

            try {
                articuloModelo.editarArticulo(selectedArticulo);
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
    private void handleDeleteArticulo() {
        int selectedIndex = articuloTable.getSelectionModel().getSelectedIndex();
        Articulo selectedArticulo = articuloTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            articuloTable.getItems().remove(selectedIndex);
            try {
                articuloModelo.eliminarArticulo(selectedArticulo.getId());
            } catch (ExcepcionArticulo e) {
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
    
    public void setMain(Main main) {
        this.main = main;

        articuloTable.setItems(main.getPersonData());
    }
}