package ru.sbercources.cinema.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends GenericDto {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'")
    private Date birthDate;
    private String phone;
    private String address;
    private String email;
    private RoleDto role;
}
