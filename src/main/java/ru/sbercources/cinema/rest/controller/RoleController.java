package ru.sbercources.cinema.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.model.Role;
import ru.sbercources.cinema.service.RoleService;

@RestController
@RequestMapping("/rest/role")
public class RoleController {
    private final RoleService roleService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public List<Role> list() {
        return roleService.getList();
    }
}
