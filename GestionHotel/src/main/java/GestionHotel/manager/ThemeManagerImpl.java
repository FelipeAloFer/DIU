package GestionHotel.manager;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThemeManagerImpl implements ThemeManager {

    private Stage stage;  // Stage de la aplicación

    public ThemeManagerImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void applyTheme(String themeName) {
        String themeFile = getThemeFile(themeName);
        if (themeFile != null) {
            // Limpiar los estilos actuales
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            // Aplicar el nuevo CSS
            scene.getStylesheets().add(getClass().getResource(themeFile).toExternalForm());
            // Recargar la escena para que se apliquen los cambios
            stage.setScene(scene);
        }
    }

    // Retorna el archivo CSS correspondiente al tema seleccionado
    private String getThemeFile(String themeName) {
        switch (themeName) {
            case "Tema Gris":
                return "/GestionHotel/tema_gris.css";
            case "Tema Negro":
                return "/GestionHotel/tema_negro.css";
            case "Tema Azul":
                return "/GestionHotel/tema_azul.css";
            case "Tema Rojo":
                return "/GestionHotel/tema_rojo.css";
            case "Tema Arcoiris":
                return "/GestionHotel/tema_arcoiris.css";
            default:
                return null;
        }
    }
}

