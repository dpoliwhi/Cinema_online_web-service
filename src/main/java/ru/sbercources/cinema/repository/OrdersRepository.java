package ru.sbercources.cinema.repository;

import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.model.User;

import java.util.List;

public interface OrdersRepository extends GenericRepository<Orders> {

    public List<Orders> findAllByUser(User user);
}
