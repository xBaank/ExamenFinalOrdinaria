package es.dam.examenjunio1ord.repositories;

import es.dam.examenjunio1ord.managers.DataBaseManager;
import es.dam.examenjunio1ord.models.Jugador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

;

public class JugadorRepo implements IJugadorRepo {
    private final DataBaseManager dataBaseManager;
    private ObservableList<Jugador> jugadores;

    public JugadorRepo(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
        jugadores = FXCollections.observableArrayList(findAll());

    }

    @Override
    public ObservableList<Jugador> findAll() {
        List<Jugador> list = new ArrayList<>();
        try (dataBaseManager) {
            dataBaseManager.open();
            String query = "SELECT * FROM tributos";
            var result = dataBaseManager.select(query).orElse(null);

            if (result == null) return FXCollections.observableArrayList(list);

            while (result.next()) {
                var value = new Jugador(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("genero"),
                        result.getInt("edad"),
                        result.getInt("distrito"),
                        result.getInt("vida"),
                        result.getInt("fuerza"),
                        result.getString("estado")
                );
                list.add(value);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();

        }
        if (jugadores == null) jugadores = FXCollections.observableArrayList();
        jugadores.setAll(list);
        return jugadores;
    }

    @Override
    public Optional<Jugador> findById(Integer id) {
        List<Jugador> personajes = new ArrayList<>();
        try (dataBaseManager) {
            dataBaseManager.open();
            String query = "SELECT * FROM tributos where id = ?";
            var result = dataBaseManager.select(query, id.toString()).orElse(null);

            if (result == null || !result.next()) return Optional.empty();

            return Optional.of(new Jugador(
                    result.getInt("id"),
                    result.getString("nombre"),
                    result.getString("mujer"),
                    result.getInt("edad"),
                    result.getInt("distrito"),
                    result.getInt("vida"),
                    result.getInt("fuerza"),
                    result.getString("fuerza")
            ));

        } catch (SQLException ignored) {
            ignored.printStackTrace();

        }
        return Optional.empty();
    }

    @Override
    public Optional<Jugador> save(Jugador entity) {
        try (dataBaseManager) {
            dataBaseManager.open();
            String query = "insert into tributos values (?, ?, ?, ?, ?, ?, ?,?)";
            var result = dataBaseManager.insert(query, entity.getId(), entity.getName(), entity.getGenero(), entity.getEdad(), entity.getDistrito(), entity.getVida(), entity.getFuerza(), entity.getEstado()).orElse(null);

            if (result == null || !result.next()) return Optional.empty();

            entity.setId(result.getInt(1));

            jugadores.add(entity);

            return Optional.of(entity);

        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Jugador> update(Jugador entity) {
        try (dataBaseManager) {
            dataBaseManager.open();
            String query = "update tributos set nombre = ?,genero = ?, edad = ?, distrito = ?, vida = ?, fuerza = ?, estado = ? where id = ?";
            var updatedCount = dataBaseManager.update(query, entity.getName(), entity.getGenero(), entity.getEdad(), entity.getDistrito(), entity.getVida(), entity.getFuerza(), entity.getEstado(), entity.getId());
            if (updatedCount == 1) {
                var updated = jugadores.stream().filter(i -> i.getId() == entity.getId()).findFirst();
                var index = jugadores.indexOf(updated.get());
                jugadores.set(index, entity);

                return Optional.of(entity);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();

        }
        return Optional.empty();
    }

    @Override
    public void delete(Jugador entity) {
        try (dataBaseManager) {
            dataBaseManager.open();
            String query = "delete from tributos where id = ?";
            dataBaseManager.delete(query, entity.getId());
            jugadores.remove(entity);
        } catch (SQLException ignored) {
            ignored.printStackTrace();

        }
    }

    @Override
    public void deleteAll() {
        try (dataBaseManager) {
            dataBaseManager.open();
            String query = "delete from tributos";
            dataBaseManager.delete(query);
            jugadores.clear();
        } catch (SQLException ignored) {
            ignored.printStackTrace();

        }
    }
}
