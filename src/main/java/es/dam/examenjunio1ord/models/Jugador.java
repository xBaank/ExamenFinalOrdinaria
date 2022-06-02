package es.dam.examenjunio1ord.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Jugador {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty genero;
    private SimpleIntegerProperty edad;
    private SimpleIntegerProperty distrito;
    private SimpleIntegerProperty vida;
    private SimpleIntegerProperty fuerza;
    private SimpleStringProperty estado;

    public Jugador(int id, String name, String genero, int edad, int distrito, int vida, int fuerza, String estado) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.genero = new SimpleStringProperty(genero);
        this.edad = new SimpleIntegerProperty(edad);
        this.distrito = new SimpleIntegerProperty(distrito);
        this.vida = new SimpleIntegerProperty(vida);
        this.fuerza = new SimpleIntegerProperty(fuerza);
        this.estado = new SimpleStringProperty(estado);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGenero() {
        return genero.get();
    }

    public SimpleStringProperty generoProperty() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public int getEdad() {
        return edad.get();
    }

    public SimpleIntegerProperty edadProperty() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public int getDistrito() {
        return distrito.get();
    }

    public SimpleIntegerProperty distritoProperty() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito.set(distrito);
    }

    public int getVida() {
        return vida.get();
    }

    public SimpleIntegerProperty vidaProperty() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida.set(vida);
    }

    public int getFuerza() {
        return fuerza.get();
    }

    public SimpleIntegerProperty fuerzaProperty() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza.set(fuerza);
    }

    public String getEstado() {
        return estado.get();
    }

    public SimpleStringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id.get() +
                ", name=" + name.get() +
                ", genero=" + genero.get() +
                ", edad=" + edad.get() +
                ", distrito=" + distrito.get() +
                ", vida=" + vida.get() +
                ", fuerza=" + fuerza.get() +
                ", estado=" + estado.get() +
                '}';
    }
}
