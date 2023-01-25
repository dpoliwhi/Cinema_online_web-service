package ru.sbercources.cinema.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.repository.DirectorsRepository;
import ru.sbercources.cinema.repository.FilmRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class DirectorsService extends GenericService<Directors> {

    private final DirectorsRepository repository;
    private final FilmRepository filmRepository;
    private final OrdersService ordersService;

    protected DirectorsService(DirectorsRepository repository, OrdersService ordersService, FilmRepository filmRepository) {
        super(repository);
        this.repository = repository;
        this.ordersService = ordersService;
        this.filmRepository = filmRepository;
    }

    @Override
    public void delete(Long id) {
        Directors directorToDel = repository.findById(id).orElse(null);
        if (directorToDel != null) {
            Set<Film> filmsOfDirector = directorToDel.getFilms();
            List<Orders> ordersOfDirector = filmsOfDirector.stream().map(ordersService::getOrderByFilm).toList();
            if (ordersOfDirector.stream().allMatch(Objects::isNull)) {
                repository.delete(directorToDel);
                filmsOfDirector
                        .stream()
                        .filter(i -> i.getDirectors().size() == 0)
                        .forEach(filmRepository::delete);
            }
        }
    }

    public Page<Directors> listAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
