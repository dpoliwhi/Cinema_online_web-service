package ru.sbercources.cinema.service;

import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.model.User;
import ru.sbercources.cinema.repository.FilmRepository;
import ru.sbercources.cinema.repository.OrdersRepository;
import ru.sbercources.cinema.repository.UserRepository;

import java.util.List;

@Service
public class OrdersService extends GenericService<Orders> {

    private final OrdersRepository repository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    protected OrdersService(OrdersRepository repository, FilmRepository filmRepository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Orders create(Orders object) {
        Film film = filmRepository.findById(object.getFilm().getId()).orElseThrow();
        User user = userRepository.findById(object.getUser().getId()).orElseThrow();
        object.setUser(user);
        object.setFilm(film);
        if (object.isPurchase()) {
            object.setRentPeriod(null);
        }
        return super.create(object);
    }

    protected List<Orders> getAllByUser(User user) {
        return repository.findAllByUser(user);
    }

    protected Orders getOrderByFilm(Film film) {
        return repository.findByFilm(film);
    }
}
