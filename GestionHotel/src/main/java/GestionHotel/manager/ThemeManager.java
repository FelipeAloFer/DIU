package GestionHotel.manager;

import javafx.scene.Scene;

public interface ThemeManager {
    void applyTheme(String themeName);
    void applyThemeToScene(Scene scene); // Nuevo método
    void registerScene(Scene scene);
    void unregisterScene(Scene scene);
}