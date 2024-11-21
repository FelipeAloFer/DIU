package PracticaEx.controller;


import PracticaEx.modelo.ArticuloModelo;
import PracticaEx.util.Articulo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class DialogoArticuloController {

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

    ArticuloModelo articuloModelo;
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

    public void setCatalogoModel(ArticuloModelo articuloModelo) {
        this.articuloModelo = articuloModelo;
    }
    public void setTotalProductos(){
        productosDisponiblesLabel.setText(String.valueOf(articuloModelo.getTotalProductos()));
    }

    public void bindVariable(IntegerProperty valorMain) {
        valorControlador.bindBidirectional(valorMain);
        productosDisponiblesLabel.textProperty().bind(valorControlador.asString());
        barra.progressProperty().bind(valorControlador.divide(50.0));
    }


}