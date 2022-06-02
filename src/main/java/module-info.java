module es.dam.examenjunio1ord {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens es.dam.examenjunio1ord to javafx.fxml;
    exports es.dam.examenjunio1ord;
    exports es.dam.examenjunio1ord.controllers;
    opens es.dam.examenjunio1ord.controllers to javafx.fxml;
}