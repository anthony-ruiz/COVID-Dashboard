package com.anthonyra95.covid.contollers;

import com.anthonyra95.covid.domain.State;
import com.anthonyra95.covid.domain.StateRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StateController {
    private final StateRepository repository;

    StateController(StateRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/states")
    List<State> all() {
        return repository.findAll();
    }

    @PostMapping("states")
    State newState(@RequestBody State newState) {
        return repository.save(newState);
    }

    // Single item

    @GetMapping("/states/{id}")
    State one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    @PutMapping("/states/{id}")
    State replaceEmployee(@RequestBody State newState, @PathVariable Long id) {

        return repository.findById(id)
                .map(state -> {
                    state.setName(newState.getName());
                    state.setPositive(newState.getPositive());
                    return repository.save(state);
                })
                .orElseGet(() -> {
                    newState.setId(id);
                    return repository.save(newState);
                });
    }

    @DeleteMapping("/states/{id}")
    void deleteState(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
