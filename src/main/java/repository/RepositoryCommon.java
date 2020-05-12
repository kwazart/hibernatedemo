package repository;

public interface RepositoryCommon<E, V> {
    E create(E e);
    E read(V id);
    E update(E e);
}
