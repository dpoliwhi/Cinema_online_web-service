package ru.sbercources.cinema.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.repository.GenericRepository;
import ru.sbercources.cinema.service.GenericService;

@RestController
@RequestMapping("/rest/orders")
public class OrdersController extends GenericController<Orders> {
    public OrdersController(GenericService<Orders> service) {
        super(service);
    }
}
