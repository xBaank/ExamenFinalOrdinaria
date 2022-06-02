package es.dam.examenjunio1ord.models;

import es.dam.examenjunio1ord.models.items.Item;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Capitolio {
    private Stack<Item> reserva = new Stack<>();
    private Queue<Jugador> cola = new PriorityQueue<>();

    public Capitolio(int size) {
        //se generan al azar
        for (int i = 0; i < size; i++) {
            reserva.push(Item.getRandomItem());
        }
    }

    public void recogerMuerto(Jugador jugador) {
        cola.add(jugador);
    }

    public Item getItem() {
        return reserva.pop();
    }
}
