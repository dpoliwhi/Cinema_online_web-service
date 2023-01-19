package ru.sbercources.cinema.rest.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.dto.OrdersDto;
import ru.sbercources.cinema.mapper.OrdersMapper;
import ru.sbercources.cinema.model.Orders;
import ru.sbercources.cinema.service.GenericService;

@RestController
@RequestMapping("/rest/orders")
public class OrdersController extends GenericController<Orders, OrdersDto> {
    public OrdersController(GenericService<Orders> service, OrdersMapper mapper) {
        super(service, mapper);
    }
}
