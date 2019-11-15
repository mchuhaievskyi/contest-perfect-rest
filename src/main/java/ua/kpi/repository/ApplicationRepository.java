package ua.kpi.repository;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityNotFoundException;

public abstract class ApplicationRepository<T, ID> implements CrudRepository<T, ID> {

    public T read(ID id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
