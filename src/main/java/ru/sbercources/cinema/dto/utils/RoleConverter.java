package ru.sbercources.cinema.dto.utils;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import ru.sbercources.cinema.dto.RoleDto;
import ru.sbercources.cinema.model.Role;

@Component
public class RoleConverter extends Converter <RoleDto, Role> {

    public RoleConverter() {
        super(RoleConverter::convertToEntity, RoleConverter::convertToDto);
    }

    private static RoleDto convertToDto(Role role) {
        return new RoleDto(role.getId(), role.getTitle(), role.getDescription());
    }

    private static Role convertToEntity(RoleDto dto) {
        return Role
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }
}
