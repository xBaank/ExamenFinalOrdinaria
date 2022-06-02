package es.dam.examenjunio1ord.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TributosCotroller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Bienvenido a los Juegos del Hambre!");
    }
}