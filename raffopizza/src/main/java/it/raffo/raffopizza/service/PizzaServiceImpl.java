package it.raffo.raffopizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.raffo.raffopizza.model.Pizza;
import it.raffo.raffopizza.repository.PizzaRepo;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Override
    public Optional<Pizza> findbyId(Integer id) {
        // TODO Auto-generated method stub
        return pizzaRepo.findById(id);

        // LO INSERIUSCE VS IN AUTOMATICO. CHE FACCIO, LASCIO ?
        // throw new UnsupportedOperationException("Unimplemented method 'findbyId'");
    }

    @Override
    public List<Pizza> findAll() {
        // TODO Auto-generated method stub
        return pizzaRepo.findAll();
    }

    @Override
    public Pizza save(Pizza pizza) {
        // TODO Auto-generated method stub
        return pizzaRepo.save(pizza);
    }

    @Override
    public Pizza update(Integer pizzaId, Pizza inputPizza) {
        // TODO Auto-generated method stub

        Optional<Pizza> updatePizza = pizzaRepo.findById(pizzaId);

        if (updatePizza.isEmpty()) {
            throw new IllegalArgumentException("La pizza cercata con id " + pizzaId + " non esiste.");
        }

        Pizza pizza = updatePizza.get();
        pizza.setName(inputPizza.getName());
        pizza.setDescription(inputPizza.getDescription());
        pizza.setPrice(inputPizza.getPrice());
        pizza.setPhoto(inputPizza.getPhoto());
        // pizza.setSale(inputPizza.getSale());

        return pizzaRepo.save(pizza);
    }

}
