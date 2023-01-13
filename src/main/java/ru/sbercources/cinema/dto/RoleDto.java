package ru.sbercources.cinema.dto;

import lombok.*;
import ru.sbercources.cinema.model.Role;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String title;
    private String description;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.title = role.getTitle();
        this.description = role.getDescription();
    }
}
