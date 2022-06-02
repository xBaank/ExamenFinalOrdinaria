package es.dam.examenjunio1ord.repositories;

import es.dam.examenjunio1ord.managers.DataBaseManager;
import es.dam.examenjunio1ord.models.Jugador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JugadorRepoTest {
    private IJugadorRepo repo = new JugadorRepo(DataBaseManager.getInstance());

    @Test
    void findAll() {
        var result = repo.findAll();

        var jugador = result.stream().filter(i -> i.getId() == 1).findFirst();
        var jugadorNotPresent = result.stream().filter(i -> i.getId() == 9999).findFirst();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(jugador.isPresent());
        Assertions.assertFalse(jugadorNotPresent.isPresent());
    }

    @Test
    void save() {
        var jugadorToSave = new Jugador(0, "a", "b", 1, 1, 1, 1, "False");
        repo.delete(jugadorToSave);
        var result = repo.save(jugadorToSave);
        var resultNot = repo.save(jugadorToSave);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertTrue(resultNot.isEmpty());
    }

    @Test
    void update() {
        var result = repo.update(new Jugador(1, "b", "b", 1, 1, 1, 1, "False"));
        var resultNot = repo.update(new Jugador(99999, "b", "b", 1, 1, 1, 1, "False"));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertTrue(resultNot.isEmpty());
    }
}