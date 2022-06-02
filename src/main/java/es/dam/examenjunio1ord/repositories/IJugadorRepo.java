package es.dam.examenjunio1ord.repositories;

import es.dam.examenjunio1ord.models.Jugador;

public interface IJugadorRepo extends ICRUDRepository<Jugador, Integer> {
    void deleteAll();
}
