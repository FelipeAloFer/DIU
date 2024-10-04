package pack.scenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pack.scenebuilder.controller.HelloController;

import java.io.IOException;

public class Contador extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(Contador.class.getResource("hello-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 420, 340);
        stage.setTitle("Contador 1");
        stage.setScene(scene1);
        stage.show();

        FXMLLoader fxmlLoader2 = new FXMLLoader(Contador.class.getResource("hello-view.fxml"));
        Stage stage2 = new Stage();
        Scene scene2 = new Scene(fxmlLoader2.load(), 420, 340);
        stage2.setTitle("Contador 2");
        stage2.setScene(scene2);
        stage2.show();

        HelloController controller = fxmlLoader1.getController();
        HelloController controller2 = fxmlLoader2.getController();
        controller.numpuls.bindBidirectional(controller2.numpuls);
    }

    public static void main(String[] args) {
        launch();
    }
}