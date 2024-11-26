package com.example.PracticaFS.controller;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import com.example.PracticaFS.MainApp;
import com.example.PracticaFS.Modelo.Articulo;
import com.example.PracticaFS.Modelo.CatalogoModelo;
import com.example.PracticaFS.util.Conversor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CatalogoController {

    @FXML
    private TableView<Articulo> articuloTable;
    @FXML
    private TableColumn<Articulo, String> nameColumn;
    @FXML
    private TableColumn<Articulo, String> precioColumn;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private Label stockLabel;
    @FXML
    private TextField unidades;
    @FXML
    private TextField total;

    private MainApp mainApp;
    private CatalogoModelo catalogoModelo;
    private Conversor conversor;
    private TotalInterface totalInterface;
    private Integer id=1;

    public void setConversor(Conversor conversor) {
        this.conversor = conversor;
    }

    public void setCatalogoModelo(CatalogoModelo catalogoModelo) {
        this.catalogoModelo = catalogoModelo;
    }

    public CatalogoController() {
        totalInterface=new TotalInterfaceImpl();
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asString());

        // Clear person details.
        showArticuloDetails(null);

        // Listen for selection changes and show the person details when changed.
        articuloTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showArticuloDetails(newValue));
    }
    public void setMainApp(MainApp mainApp) throws ExcepcionArticulo {
        this.mainApp = mainApp;
        // Add observable list data to the table
        articuloTable.setItems(mainApp.getArticulosData());
    }
    private void showArticuloDetails(Articulo articulo) {
        if (articulo != null) {
            nombreLabel.setText(articulo.getNombre());
            descLabel.setText(articulo.getDescripcion());
            precioLabel.setText(String.valueOf(articulo.getPrecio()));
            stockLabel.setText(Integer.toString(articulo.getStock()));
        } else {
            nombreLabel.setText("");
            descLabel.setText("");
            precioLabel.setText("");
            stockLabel.setText("");
        }
    }

    public void handleNewArticulo(ActionEvent actionEvent) {
        Articulo tempArticulo=new Articulo();
        boolean okClicked=mainApp.showNuevoArticulo(tempArticulo);
        if (okClicked) {
            try{
                tempArticulo.setCodigo(id++);
                ArticuloVO articuloVO=new ArticuloVO();
                articuloVO=conversor.convertirArticulo(tempArticulo);
                catalogoModelo.addArticulo(articuloVO);
                catalogoModelo.incNumeroArticulos();
                mainApp.getArticulosData().add(tempArticulo);

            }catch(ExcepcionArticulo e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al añadir la persona");
                alert.setTitle("Error con la base de datos");
                alert.setContentText("No se puede conectar con la base de datos para añadir la persona");
                alert.showAndWait();
            }
        }
    }

    public void handleTotal(ActionEvent actionEvent) {
       try {
            if(Integer.parseInt(stockLabel.getText())>=Integer.parseInt(unidades.getText())){
                if(Integer.parseInt(unidades.getText())>=0) {
                    catalogoModelo.setTotalInterface(totalInterface);
                    total.setText(String.valueOf(catalogoModelo.total(Integer.parseInt(unidades.getText()),Float.parseFloat(precioLabel.getText()))));
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error con las unidades");
                    alert.setTitle("El numero es incorrecto");
                    alert.setContentText("Las unidades no pueden ser negativas");
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al calcular");
                alert.setTitle("Error con las unidades");
                alert.setContentText("Las unidades no pueden superar al stock actual");
                alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al calcular");
            alert.setTitle("Error con las unidades");
            alert.setContentText("Debe introducir un numero de unidades y seleccionar al articulo");
            alert.showAndWait();
        }
    }
}
