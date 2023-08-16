package repository;

import music.model.Identifiable;

public interface Repository<T extends Identifiable<Tid>,Tid> {
    T add(T elem);
    void delete(T elem);
    void update(T elem, Tid id);
    T findById(Tid id);
    Iterable<T> findAll();
}
