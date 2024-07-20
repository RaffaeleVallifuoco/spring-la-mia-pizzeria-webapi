package it.raffo.raffopizza.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.raffo.raffopizza.model.Pizza;
import it.raffo.raffopizza.response.Payload;
import it.raffo.raffopizza.service.PizzaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequestMapping("/api/raffoPizza")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/index")
    public ResponseEntity<Payload<List<Pizza>>> getAll() {
        List<Pizza> pizzas = pizzaService.findAll();
        return ResponseEntity.ok(new Payload<List<Pizza>>(pizzas, null, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payload<Pizza>> get(@PathVariable("id") Integer pizzaId) {

        Optional<Pizza> pizza = pizzaService.findbyId(pizzaId);

        if (pizza.isPresent()) {
            return ResponseEntity.ok(new Payload<Pizza>(pizza.get(), null, HttpStatus.OK));
            // return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new Payload<Pizza>(null, "Pizza con id " + pizzaId + " non trovata", HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);

            // SOLUZIONE GIUSEPPE DA LIPARI CHE NON DUNZIONA CON LA SPECIFICA DI TIPO

            // return new ResponseEntity<Pizza>(
            // new Payload<Pizza>(null, "Pizza con id " + pizzaId + " non trovata",
            // HttpStatus.NOT_FOUND),
            // HttpStatus.NOT_FOUND);

            // SOLUZIONE IBRIDA CHE FUNZIONA MA METTO STATUS OK

            // return ResponseEntity.ok(
            // new Payload<Pizza>(null, "Pizza con id " + pizzaId + " non trovata",
            // HttpStatus.NOT_FOUND));

            // SOLUZIONE BRUTTA SENZA SPECIFICA DEL TIPO

            // return new ResponseEntity<>(pizza.get(), HttpStatus.NOT_FOUND);

        }

    }

}
