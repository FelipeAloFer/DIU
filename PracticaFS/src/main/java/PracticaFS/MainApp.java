package PracticaFS;

import Modelo.ArticuloVO;
import Modelo.ExcepcionArticulo;
import Modelo.Repository.ArticuloRepository;
import Modelo.Repository.Impl.ArticuloRepositoryJDBC;
import PracticaFS.controller.CatalogoController;
import PracticaFS.controller.NuevoArticuloController;
import PracticaFS.modelo.Articulo;
import PracticaFS.modelo.CatalogoModelo;
import PracticaFS.util.Conversor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class MainApp extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;
    private CatalogoModelo catalogoModelo;
    private Conversor conversor;
    private ArticuloRepository articuloRepository;

    private ObservableList<Articulo> articulosData = FXCollections.observableArrayList();

    public MainApp() {
        // Add some sample data
        articulosData.addAll(addList());
    }
    public ArrayList<Articulo> addList(){
        catalogoModelo=new CatalogoModelo();
        conversor=new Conversor();
        articuloRepository = new ArticuloRepositoryJDBC();
        catalogoModelo.setArticuloRepository(articuloRepository);
        ArrayList<ArticuloVO>listaArticuloVO = new ArrayList<ArticuloVO>();
        ArrayList<Articulo> listaArticulo= new ArrayList<Articulo>();
        try{
            listaArticuloVO = catalogoModelo.obternerListaArticulos();
            catalogoModelo.setNumeroArticulos(listaArticuloVO.size());
        } catch (ExcepcionArticulo e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
        listaArticulo = conversor.lista(listaArticuloVO);
        return listaArticulo;
    }

    public ObservableList<Articulo> getArticulosData() {
        return articulosData;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestor catalogo");

        showCatalogoOverview();

    }
    public void showCatalogoOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PracticaFS/Catalogo.fxml"));
            AnchorPane catalogoOverview = loader.load();

            Scene scene = new Scene(catalogoOverview);
            primaryStage.setScene(scene);
            primaryStage.show();

            CatalogoController controller = loader.getController();
            controller.setCatalogoModelo(catalogoModelo);
            controller.setConversor(conversor);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExcepcionArticulo e) {
            throw new RuntimeException(e);
        }
    }

    public boolean showNuevoArticulo(Articulo articulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PracticaFS/NuevoArticulo.fxml"));
            AnchorPane nuevoArticuloOverview = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Articulo");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(nuevoArticuloOverview);
            dialogStage.setScene(scene);

            NuevoArticuloController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCatalogoModelo(catalogoModelo);
            controller.setArticulo(articulo);

            controller.setNumeroAD();
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
