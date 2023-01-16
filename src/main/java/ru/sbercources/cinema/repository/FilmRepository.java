package ru.sbercources.cinema.repository;

import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Genre;

import java.util.List;

public interface FilmRepository extends GenericRepository<Film> {

    public List<Film> findAllByTitleOrCountryOrGenre(String title, String country, Genre genre);
}
