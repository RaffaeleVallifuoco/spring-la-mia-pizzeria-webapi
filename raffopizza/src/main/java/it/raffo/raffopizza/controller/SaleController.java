package it.raffo.raffopizza.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import it.raffo.raffopizza.model.Pizza;
import it.raffo.raffopizza.model.Sale;
import it.raffo.raffopizza.repository.PizzaRepo;
import it.raffo.raffopizza.repository.SaleRepo;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private PizzaRepo pizzaRepo;

    @GetMapping
    public String getSaleList(Model model) {

        java.util.List<Sale> saleList = saleRepo.findAll();
        model.addAttribute("list", saleList);

        return "/sale/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Pizza> pizzaTarget = pizzaRepo.findAll();

        // if (pizzaTarget == null) {
        // return "redirect:/index";
        // }

        model.addAttribute("sale", new Sale());
        model.addAttribute("pizzaTarget", pizzaTarget);
        System.err.println("if get");

        return "sale/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("sale") Sale saleForm, BindingResult bindingresult, Model model) {
        // TODO: process POST request

        System.err.println("prima if");
        if (bindingresult.hasErrors()) {
            System.out.println("dentro if errore");

            return "/sale/create";
        }
        System.err.println("fuori if errore");

        System.out.println(saleForm.getPizza());

        saleRepo.save(saleForm);

        return "redirect:/sale";

    }

}
