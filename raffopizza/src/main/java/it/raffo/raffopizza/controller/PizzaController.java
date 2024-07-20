package it.raffo.raffopizza.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.el.stream.Optional;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import it.raffo.raffopizza.repository.PizzaRepo;
import it.raffo.raffopizza.repository.SaleRepo;
import it.raffo.raffopizza.model.Pizza;
import it.raffo.raffopizza.model.Sale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/index")
public class PizzaController {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Autowired
    private SaleRepo saleRepo;

    @GetMapping
    public String index(Model model, @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description) {

        java.util.List<Pizza> pizzeList = new ArrayList<>();

        if (name == null && description == null) {

            pizzeList = pizzaRepo.findAll();

        } else if (name == null) {
            pizzeList = pizzaRepo.findByDescriptionContainingIgnoreCase(description);
        } else {

            pizzeList = pizzaRepo.findByNameContainingIgnoreCase(name);
        }

        model.addAttribute("list", pizzeList);

        return "/pizza/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("pizza", pizzaRepo.getReferenceById(id));

        return "/pizza/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());

        return "/pizza/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingresult, Model model) {
        // TODO: process POST request
        System.out.println("prima dell'if");
        System.out.println(pizzaForm.getName());
        System.out.println(pizzaForm.getDescription());

        if (bindingresult.hasErrors()) {
            System.out.println("dentro if");
            return "/pizza/create";
        }

        pizzaRepo.save(pizzaForm);

        return "redirect:/index";

    }

    @GetMapping("/carousel")
    public String photo(Model model) {

        model.addAttribute("pizza", pizzaRepo.findAll());

        return "/pizza/carousel";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("pizza", pizzaRepo.findById(id).get());

        return "/pizza/edit";
    }

    @PostMapping("/edit/{id}")
    public String Update(@Valid @ModelAttribute("pizza") Pizza pizzaUpdate, BindingResult bindingresult, Mode model) {

        if (bindingresult.hasErrors()) {
            return "/pizza/edit";
        }

        pizzaRepo.save(pizzaUpdate);

        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        // TODO: process POST request

        pizzaRepo.deleteById(id);

        return "redirect:/index";
    }

    @GetMapping("/{id}/sale")
    public String getSale(@PathVariable("id") Integer id, Model model) {

        Pizza pizza = pizzaRepo.findById(id).get();
        Sale sale = new Sale();
        sale.setPizza(pizza);

        model.addAttribute("sale", sale);

        return "/pizza/index";
        
    }

}
