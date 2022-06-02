package es.dam.examenjunio1ord.controllers;

import es.dam.examenjunio1ord.JuegosHambreApp;
import es.dam.examenjunio1ord.managers.DataBaseManager;
import es.dam.examenjunio1ord.models.Capitolio;
import es.dam.examenjunio1ord.models.Jugador;
import es.dam.examenjunio1ord.repositories.IJugadorRepo;
import es.dam.examenjunio1ord.repositories.ItemsMap;
import es.dam.examenjunio1ord.repositories.JugadorRepo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class TributosCotroller {


    private IJugadorRepo repository = new JugadorRepo(DataBaseManager.getInstance());
    //100 items
    private Capitolio capitolio;

    private ItemsMap items;

    @FXML
    private TableView<Jugador> jugadorTableView;

    @FXML
    private TableColumn<Jugador, String> nombre;

    @FXML
    private TableColumn<Jugador, Integer> distrito;
    @FXML
    private TableColumn<Jugador, String> estado;
    @FXML
    private ComboBox<Integer> mapaSize;
    @FXML
    private TextArea output;


    @FXML
    private void initialize() {
        jugadorTableView.setItems(repository.findAll());
        nombre.setCellValueFactory(i -> i.getValue().nameProperty());
        distrito.setCellValueFactory(i -> i.getValue().distritoProperty().asObject());
        estado.setCellValueFactory(i -> i.getValue().estadoProperty());

        mapaSize.setItems(FXCollections.observableArrayList(6, 7, 8));

        loadCsv();
        jugadorTableView.refresh();
    }

    private void loadCsv() {
        var stage = JuegosHambreApp.mainStage;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
        var file = fileChooser.showOpenDialog(stage);
        if (file == null) return;
        try {

            var list = new ArrayList<Jugador>();
            var jugadores = Files.lines(file.toPath()).skip(1).map(this::parse).collect(Collectors.toList());
            repository.deleteAll();
            jugadores.forEach(jugador -> repository.save(jugador));

        } catch (Exception e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fallo al cargar csv");
            alert.setHeaderText("Fallo al cargar csv");
            alert.showAndWait();
        }
    }

    @FXML
    private void informe() {
        var stage = JuegosHambreApp.mainStage;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        var file = fileChooser.showSaveDialog(stage);
        if (file == null) return;

        StringBuilder stringBuilder = new StringBuilder();
        var list = repository.findAll();

        try (FileWriter writer = new FileWriter(file)) {
            var groupedByDistrito = list.stream().collect(Collectors.groupingBy(Jugador::getDistrito));
            stringBuilder.append("Agrupados por distrito \n");

            groupedByDistrito.forEach((i, b) -> {
                stringBuilder.append("Distrito ").append(i).append(": \n");
                stringBuilder.append(b).append("\n");
            });
            stringBuilder.append("\n");


            var conMasFuerza = list.stream().max(Comparator.comparing(Jugador::getFuerza));
            stringBuilder.append("Con mas fuerza \n");
            stringBuilder.append(conMasFuerza.get());
            stringBuilder.append("\n");


            var groupedByGenre = list.stream().collect(Collectors.groupingBy(Jugador::getGenero));
            stringBuilder.append("Agrupador por genero \n");

            groupedByGenre.forEach((i, b) -> {
                stringBuilder.append("Genero ").append(i).append(": \n");
                stringBuilder.append(b).append("\n");
            });

            stringBuilder.append("\n");

            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Jugador parse(String text) {
        var lines = text.split(",");

        var id = Integer.parseInt(lines[0]);
        var nombre = lines[1];
        var genero = lines[2];
        var edad = Integer.parseInt(lines[3]);
        var distrito = Integer.parseInt(lines[4]);
        var vida = Integer.parseInt(lines[5]);
        var fuerza = Integer.parseInt(lines[6]);
        var estado = lines[7];

        return new Jugador(id, nombre, genero, edad, distrito, vida, fuerza, estado);
    }

    public void salir(ActionEvent event) {
        JuegosHambreApp.mainStage.close();
    }

    public void iniciar(ActionEvent event) {
        //Inizializar
        if (mapaSize.getSelectionModel().getSelectedItem() == null) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selecciona una dimension");
            alert.setHeaderText("Selecciona una dimension");
            alert.show();
            return;
        }


        var size = mapaSize.getSelectionModel().getSelectedItem();

        output.appendText("\n Cargando items del capitolio y mapa");

        items = new ItemsMap(size);
        capitolio = new Capitolio(100);

        output.appendText("\n cargando items del mapa");

        for (int i = 0; i < items.getSize(); i++) {
            var random = new Random().nextInt(items.getSize());
            var random2 = new Random().nextInt(items.getSize());
            items.setItem(random, random2, capitolio.getItem());
        }

        output.appendText("\n colocando jugadores en el mapa");

        for (Jugador jugador : repository.findAll()) {
            int random;
            int random2;
            do {
                random = new Random().nextInt(items.getSize());
                random2 = new Random().nextInt(items.getSize());
            }
            while (!items.setJugador(random, random2, jugador));
            output.appendText("\n Colocado jugador " + jugador.getName() + " en el mapa");
        }

        //Reactividad

        //se busca a annie y se borra si esta
        var jugador = repository.findById(5);
        if (jugador.isPresent()) {
            repository.delete(jugador.get());
            jugadorTableView.refresh();
        }


    /*while (!items.isOnePlayer()) {

        //Simulacion
    }*/


    }
}