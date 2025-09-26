package com.paul.routines;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/routines")
public class RoutineController {
    private final RoutineService service;

    public RoutineController(RoutineService service) { this.service = service; }

    @GetMapping
    public List<Routine> list(@RequestParam(required = false) Long clientId) {
        return (clientId == null) ? service.list() : service.byClient(clientId);
    }

    @GetMapping("/{id}")
    public Routine get(@PathVariable Long id) { return service.get(id); }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Routine create(@Valid @RequestBody Routine body) { return service.create(body); }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Routine update(@PathVariable Long id, @RequestBody Routine body) { return service.update(id, body); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
