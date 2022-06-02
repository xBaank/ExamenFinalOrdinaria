package es.dam.examenjunio1ord;

import es.dam.examenjunio1ord.managers.DataBaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JuegosHambreApp extends Application {
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(JuegosHambreApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 520);
        stage.setTitle("Juegos del Hambre");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        checkServer();
        launch();
    }

    private static void checkServer() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseManager controller = DataBaseManager.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello world'");
            if (rs.isPresent()) {
                rs.get().next();
                controller.close();
                System.out.println("Conexión correcta a la Base de Datos");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }
}