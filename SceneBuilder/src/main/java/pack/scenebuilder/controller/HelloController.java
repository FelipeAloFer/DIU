package pack.scenebuilder.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class HelloController {
    @FXML
    public Button btnMas;
    public Button btnCero;
    public Button btnMenos;
    public VBox raiz;
    public Label numero;
    public TextField cuadro;
    public ProgressBar barra;
    public SimpleIntegerProperty numpuls = new SimpleIntegerProperty();

    @FXML
    public void initialize(){
        raiz.getStylesheets().add(getClass().getResource("/styles/contador.css").toExternalForm());
        barra.progressProperty().bind(numpuls.divide(50.0));
        numpuls.addListener((obs, oldVal, newVal) -> numero.setText(String.valueOf(newVal)));
    }

    public void handleKeyPressed(KeyEvent event) {
        // Verifica si se presionó la tecla Enter
        if (event.getCode() == KeyCode.ENTER) {
            // Lógica para cuando se presiona Enter
            if (isNumeric(cuadro.getText())) {
                numero.setText(cuadro.getText());
                numpuls.setValue(Integer.valueOf(numero.getText()));

            } else {
                numero.setText("0");
                numpuls.setValue(0);

            }
        }
    }

    //Metodo cuando se pulsa boton mas
    public void botonmas(){
        numpuls.setValue(Integer.valueOf(numero.getText()));
        numpuls.setValue(numpuls.getValue()+1);
        numero.setText(String.valueOf(numpuls.getValue()));

    }
    public void botonmenos(){
        numpuls.setValue(Integer.valueOf(numero.getText()));
        numpuls.setValue(numpuls.getValue()-1);
        numero.setText(String.valueOf(numpuls.getValue()));
    }
    public void botoncero(){
        numpuls.setValue(0);
        numero.setText(String.valueOf(numpuls.getValue()));
    }

    //Metodo para comprobar que se introduce un numero (tomado de Internet(que grandes los del Internet))
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str); // Puedes usar Integer.parseInt si solo deseas enteros
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}