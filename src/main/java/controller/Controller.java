package controller;

import java.util.List;

public interface Controller<E, ID> {
    E create(E e);

    E read(ID id);

    List<E> readAll();

    E update(E e);
}
