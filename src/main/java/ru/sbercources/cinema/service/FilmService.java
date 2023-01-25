package ru.sbercources.cinema.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Genre;
import ru.sbercources.cinema.repository.FilmRepository;

import java.util.List;
import java.util.Set;

@Service
public class FilmService extends GenericService<Film> {
    private final FilmRepository repository;

    protected FilmService(FilmRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Film addDirector(Long id, List<Directors> directorsToAdd) {
        Film film = repository.findById(id).orElseThrow();
        Set<Directors> directorsSet = film.getDirectors();
        directorsSet.addAll(directorsToAdd);
        film.setDirectors(directorsSet);
        return repository.save(film);
    }

    public List<Film> search(String title, String country, Genre genre) {
        return repository.findAllByTitleOrCountryOrGenre(title, country, genre);
    }

    public Page<Film> listAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
