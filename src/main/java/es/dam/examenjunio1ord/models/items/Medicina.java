package es.dam.examenjunio1ord.models.items;

public class Medicina extends Item {
    private int nivelVida;

    public int getNivelVida() {
        return nivelVida;
    }

    public Medicina setNivelVida(int nivelVida) {
        this.nivelVida = nivelVida;
        return this;
    }
}
