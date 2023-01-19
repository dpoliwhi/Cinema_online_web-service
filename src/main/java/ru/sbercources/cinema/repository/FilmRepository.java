package ru.sbercources.cinema.repository;

import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Genre;

import java.util.List;
import java.util.Set;

public interface FilmRepository extends GenericRepository<Film> {

    public List<Film> findAllByTitleOrCountryOrGenre(String title, String country, Genre genre);

    public Set<Film> findAllByIdIn(Set<Long> ids);
}
