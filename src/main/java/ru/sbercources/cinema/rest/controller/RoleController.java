package ru.sbercources.cinema.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sbercources.cinema.model.Role;
import ru.sbercources.cinema.repository.RoleRepository;

@RestController
@RequestMapping("/rest/role")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/list")
    public List<Role> list() {
        return roleRepository.findAll();
    }
}
