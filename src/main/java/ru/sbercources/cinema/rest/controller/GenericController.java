package ru.sbercources.cinema.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.dto.GenericDto;
import ru.sbercources.cinema.mapper.GenericMapper;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.service.GenericService;

import java.util.List;


@RestController
public abstract class GenericController<T extends GenericModel, N extends GenericDto> {
    private final GenericService<T> service;
    private final GenericMapper<T, N> mapper;

    public GenericController(GenericService<T> service, GenericMapper<T, N> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ResponseBody
    @GetMapping("/list")
    @Operation(description = "Получить список всех записей", method = "GetAll")
    public ResponseEntity<List<N>> getAll() {
        return ResponseEntity.ok().body(service.getList().stream().map(mapper::toDto).toList());
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(description = "Получить запись по id", method = "GetById")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(mapper.toDto(service.getOneById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such element with id " + id);
        }
    }

    @ResponseBody
    @PostMapping
    @Operation(description = "Создать новую запись", method = "Create")
    public ResponseEntity<N> create(@RequestBody N dto) {
        return ResponseEntity.ok().body(mapper.toDto(service.create(mapper.toEntity(dto))));
    }

    @ResponseBody
    @PutMapping("{id}")
    @Operation(description = "Обновить запись по id", method = "Update")
    public ResponseEntity<N> update(@PathVariable Long id, @RequestBody N dto) {
        dto.setId(id);
        return ResponseEntity.ok().body(mapper.toDto(service.update(mapper.toEntity(dto))));
    }

    @ResponseBody
    @DeleteMapping("{id}")
    @Operation(description = "Удалить запись по id", method = "Delete")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
