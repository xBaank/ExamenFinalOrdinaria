package es.dam.examenjunio1ord.models;

import es.dam.examenjunio1ord.models.items.Item;

public class CellMap {
    private Item item;
    private Jugador jugador;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
