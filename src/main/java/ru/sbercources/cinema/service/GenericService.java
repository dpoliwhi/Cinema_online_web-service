package ru.sbercources.cinema.service;

import java.util.*;

import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.repository.GenericRepository;

@Service
public abstract class GenericService<T extends GenericModel> {
    private final GenericRepository<T> repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> getList() {
        return repository.findAll();
    }

    public T getOneById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public T create(T object) {
        return repository.save(object);
    }

    public T update(T object) {
        return repository.save(object);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
