package ru.sbercources.cinema.MVC.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sbercources.cinema.dto.UserDto;
import ru.sbercources.cinema.mapper.UserMapper;
import ru.sbercources.cinema.service.UserService;

@Slf4j
@Hidden
@Controller
@RequestMapping("/users")
public class MVCUserController {

    private final UserService service;
    private final UserMapper mapper;

    public MVCUserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDto userDto) {

        service.create(mapper.toEntity(userDto));
        return "redirect:login";
    }
}
