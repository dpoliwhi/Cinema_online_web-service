package ru.sbercources.cinema.MVC.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.dto.DirectorsDto;
import ru.sbercources.cinema.mapper.DirectorsMapper;
import ru.sbercources.cinema.model.Directors;
import ru.sbercources.cinema.service.DirectorsService;

import java.util.List;

@Controller
@RequestMapping("/directors")
public class MVCDirectorController {

    private final DirectorsService service;
    private final DirectorsMapper mapper;

    public MVCDirectorController(DirectorsService service, DirectorsMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "directorsFio"));
        Page<Directors> onePage = service.listAllPaginated(pageRequest);
        List<DirectorsDto> directorsDtos = onePage
                .stream()
                .map(mapper::toDto)
                .toList();
        model.addAttribute("directors", new PageImpl<>(directorsDtos, pageRequest, onePage.getTotalElements()));
        return "directors/viewAllDirectors";
    }

    @GetMapping("/add")
    public String create() {
        return "directors/addDirector";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("directorsForm") DirectorsDto directorsDto) {
        service.create(mapper.toEntity(directorsDto));
        return "redirect:/directors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/directors";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("director", mapper.toDto(service.getOneById(id)));
        return "directors/updateDirector";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("directorForm") DirectorsDto directorsDto) {
        service.update(mapper.toEntity(directorsDto));
        return "redirect:/directors";
    }
}
