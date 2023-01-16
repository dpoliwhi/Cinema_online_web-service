package ru.sbercources.cinema.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.model.User;
import ru.sbercources.cinema.repository.GenericRepository;
import ru.sbercources.cinema.repository.UserRepository;

@Service
public class UserService extends GenericService<User>{

    private final OrdersService ordersService;
    private final UserRepository repository;
    protected UserService(OrdersService ordersService, UserRepository repository) {
        super(repository);
        this.repository = repository;
        this.ordersService = ordersService;
    }

     public List<Film> getOrderedFilms(Long id) {
        User user = repository.findById(id).orElseThrow();
        List<Orders> orders = ordersService.getAllByUser(user);
        return orders.stream().map(Orders::getFilm).collect(Collectors.toList());
     }
}
