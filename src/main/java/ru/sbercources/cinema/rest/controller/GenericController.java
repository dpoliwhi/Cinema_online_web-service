package ru.sbercources.cinema.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbercources.cinema.model.GenericModel;
import ru.sbercources.cinema.repository.GenericRepository;
import ru.sbercources.cinema.service.GenericService;


@RestController
public abstract class GenericController<T extends GenericModel> {
    private final GenericService<T> service;

    public GenericController(GenericService<T> service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/list")
    @Operation(description = "Получить список всех записей", method = "GetAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(service.getList());
    }

    @ResponseBody
    @GetMapping("/{id}")
    @Operation(description = "Получить запись по id", method = "GetById")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(service.getOneById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such element with id " + id);
        }
    }

    @ResponseBody
    @PostMapping
    @Operation(description = "Создать новую запись", method = "Create")
    public ResponseEntity<?> create(@RequestBody T entity) {
        return ResponseEntity.ok().body(service.create(entity));
    }

    @ResponseBody
    @PutMapping("{id}")
    @Operation(description = "Обновить запись по id", method = "Update")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody T entity) {
        entity.setId(id);
        return ResponseEntity.ok().body(service.update(entity));
    }

    @ResponseBody
    @DeleteMapping("{id}")
    @Operation(description = "Удалить запись по id", method = "Delete")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
