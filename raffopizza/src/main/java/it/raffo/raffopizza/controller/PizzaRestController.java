package it.raffo.raffopizza.controller;

import it.raffo.raffopizza.response.Payload;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.MultipartStream.IllegalBoundaryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/raffoPizza")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/index")
    public ResponseEntity<Payload<List<Pizza>>> getAll() {
        List<Pizza> pizzaList = pizzaService.findAll();
        return ResponseEntity.ok(new Payload<List<Pizza>>(pizzaList, null, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payload<Pizza>> getId(@PathVariable("id") Integer pizzaId) {

        Optional<Pizza> pizza = pizzaService.findbyId(pizzaId);

        if (pizza.isPresent()) {
            return ResponseEntity.ok(new Payload<Pizza>(pizza.get(), null, HttpStatus.OK));
            // return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Payload<Pizza>>(
                    new Payload<Pizza>(null, "Pizza con id " + pizzaId + " non trovata", HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);
            // ResponseEntity.notFound() {
            // new Payload<>()
            // }

            // SOLUZIONE GIUSEPPE DA LIPARI CHE NON FUNZIONA CON LA SPECIFICA DI TIPO

            // return new ResponseEntity<Payload<Pizza>>(
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

    @PostMapping("/create")
    public ResponseEntity<Payload<Pizza>> store(@RequestBody Pizza pizza) {
        // TODO: process POST request
        try {
            Pizza storedPizza = pizzaService.save(pizza);
            return ResponseEntity.ok(new Payload<Pizza>(storedPizza, null, HttpStatus.OK));
        } catch (Exception e) {

        }
        return ResponseEntity.ok(new Payload<Pizza>(null, "Errore", HttpStatus.INTERNAL_SERVER_ERROR));
    }
    // PERCHE' DEVO METTERE SOLO RESPONSEENTITY.OK ?!

    @PutMapping("/{id}")
    public ResponseEntity<Payload<Pizza>> update(@PathVariable("id") Integer pizzaId, @Valid @RequestBody Pizza pizza) {
        // TODO: process PUT request
        try {
            pizzaService.update(pizzaId, pizza);
            return ResponseEntity.ok(new Payload<Pizza>(pizza, null, HttpStatus.OK));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new Payload<Pizza>(null, "Pizza con id " + pizzaId + " non trovata", HttpStatus.NOT_FOUND),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Payload<Pizza>(null, "Pizza con id " + pizzaId + " non trovata", HttpStatus.NOT_FOUND),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
