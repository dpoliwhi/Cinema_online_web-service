package ru.sbercources.cinema.service;

import org.springframework.stereotype.Service;
import ru.sbercources.cinema.model.Role;
import ru.sbercources.cinema.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getList() {
        return roleRepository.findAll();
    }

    public Role getOne(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }
}
