package ru.sbercources.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Role;
import ru.sbercources.cinema.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getList() {
        return roleRepository.findAll();
    }
}
