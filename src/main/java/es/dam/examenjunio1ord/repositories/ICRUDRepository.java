package es.dam.examenjunio1ord.repositories;

import javafx.collections.ObservableList;

import java.util.Optional;

public interface ICRUDRepository<T, ID> {
    ObservableList<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> save(T item);

    Optional<T> update(T item);

    void delete(T item);
}
