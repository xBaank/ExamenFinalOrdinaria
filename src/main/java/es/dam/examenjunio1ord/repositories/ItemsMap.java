package es.dam.examenjunio1ord.repositories;

import es.dam.examenjunio1ord.models.CellMap;
import es.dam.examenjunio1ord.models.Jugador;
import es.dam.examenjunio1ord.models.items.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsMap {
    private final int size;

    public int getSize() {
        return size;
    }

    public ItemsMap(int size) {
        this.size = size;
        items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            var row = new ArrayList<CellMap>();
            for (int i1 = 0; i1 < size; i1++) {
                row.add(new CellMap());
            }
            items.add(row);
        }
    }

    private List<List<CellMap>> items;

    public void setItem(int x, int y, Item item) {
        var cellmap = items.get(x).get(y);
        cellmap.setItem(item);
    }

    public boolean setJugador(int x, int y, Jugador item) {
        var cellmap = items.get(x).get(y);

        if (cellmap.getJugador() != null) return false;

        cellmap.setJugador(item);
        return true;
    }

    public boolean isOnePlayer() {
        var jugadoresVivos = 0;
        for (List<CellMap> item : items) {
            for (CellMap cellMap : item) {
                if (cellMap.getJugador() != null)
                    jugadoresVivos++;
                if (jugadoresVivos > 1)
                    return false;
            }
        }
        return jugadoresVivos == 1;
    }

}
