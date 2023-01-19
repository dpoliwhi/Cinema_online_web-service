package ru.sbercources.cinema.repository;

import ru.sbercources.cinema.model.Directors;

import java.util.Set;

public interface DirectorsRepository extends GenericRepository<Directors> {

    public Set<Directors> findAllByIdIn(Set<Long> ids);
}
