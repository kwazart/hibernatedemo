package repository;

import java.util.List;

public interface GenericRepository<E, V> {
    E save(E e);
    E read(V id);
    List<E> getAll();
    E update(E e);
}
