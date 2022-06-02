package es.dam.examenjunio1ord.models.items;

public class Armas extends Item {
    private int nivelFuerza;

    public int getNivelFuerza() {
        return nivelFuerza;
    }

    public Armas setNivelFuerza(int nivelFuerza) {
        this.nivelFuerza = nivelFuerza;
        return this;
    }
}
