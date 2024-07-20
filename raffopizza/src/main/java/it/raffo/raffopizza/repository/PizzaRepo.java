package it.raffo.raffopizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.raffo.raffopizza.model.Pizza;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

    public List<Pizza> findByNameContainingIgnoreCase(String name);

    public List<Pizza> findByDescriptionContainingIgnoreCase(String description);
}
