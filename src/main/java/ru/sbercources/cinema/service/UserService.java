package ru.sbercources.cinema.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Film;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.model.User;
import ru.sbercources.cinema.repository.UserRepository;

@Service
public class UserService extends GenericService<User>{

    private final OrdersService ordersService;
    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    protected UserService(
            OrdersService ordersService,
            UserRepository repository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            RoleService roleService) {
        super(repository);
        this.repository = repository;
        this.ordersService = ordersService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User create(User user) {
        user.setCreatedBy("REGISTRATION");
        user.setCreatedWhen(LocalDateTime.now());
        user.setRole(roleService.getOne(1L));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

     public List<Film> getOrderedFilms(Long id) {
        User user = repository.findById(id).orElseThrow();
        List<Orders> orders = ordersService.getAllByUser(user);
        return orders.stream().map(Orders::getFilm).collect(Collectors.toList());
     }
}
