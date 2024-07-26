package it.raffo.raffopizza.service;

import java.util.List;
import java.util.Optional;

import it.raffo.raffopizza.model.Pizza;

public interface PizzaService {

    public Optional<Pizza> findbyId(Integer id);

    public List<Pizza> findAll();

    public Pizza save(Pizza savePizza);

    public Pizza update(Integer pizzaId, Pizza updatePizza);
}
