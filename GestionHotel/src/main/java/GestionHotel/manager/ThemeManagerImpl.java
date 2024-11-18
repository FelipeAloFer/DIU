package GestionHotel.manager;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ThemeManagerImpl implements ThemeManager {

    private Stage stage;
    private String currentTheme = "/GestionHotel/tema_gris.css"; // Tema predeterminado
    private final List<Scene> registeredScenes = new ArrayList<>(); // Lista de escenas registradas

    public ThemeManagerImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void applyTheme(String themeName) {
        String themeFile = getThemeFile(themeName);
        if (themeFile != null) {
            currentTheme = themeFile;

            // Aplicar tema a la escena principal
            Scene scene = stage.getScene();
            if (scene != null) {
                scene.getStylesheets().clear();
                scene.getStylesheets().add(getClass().getResource(themeFile).toExternalForm());
            }

            // Aplicar tema a todas las escenas registradas
            for (Scene registeredScene : registeredScenes) {
                registeredScene.getStylesheets().clear();
                registeredScene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
            }
        }
    }

    @Override
    public void applyThemeToScene(Scene scene) {
        if (scene != null && currentTheme != null) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());
        }
    }

    @Override
    public void registerScene(Scene scene) {
        if (!registeredScenes.contains(scene)) {
            registeredScenes.add(scene);
            // Aplicar el tema actual al registrar
            applyThemeToScene(scene);
        }
    }

    @Override
    public void unregisterScene(Scene scene) {
        registeredScenes.remove(scene);
    }

    private String getThemeFile(String themeName) {
        switch (themeName) {
            case "Tema Gris":
                return "/GestionHotel/tema_gris.css";
            case "Tema Rojo":
                return "/GestionHotel/tema_rojo.css";
            case "Tema Azul":
                return "/GestionHotel/tema_azul.css";
            case "Tema Verde":
                return "/GestionHotel/tema_verde.css";
            case "Tema Arcoiris":
                return "/GestionHotel/tema_arcoiris.css";
            case "Tema Jacobo":
                return "/GestionHotel/tema_jacobo.css";
            default:
                return null;
        }
    }
}
