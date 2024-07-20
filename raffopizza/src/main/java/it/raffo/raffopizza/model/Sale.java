package it.raffo.raffopizza.model;

import java.time.LocalDate;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

@Entity
@Table(name = "SALE")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Inserisci la data di inizio offerta")
    @Column(name = "START_SALE", nullable = false)
    private LocalDate startSale;

    @NotNull(message = "Inserisci la data di fine offerta")
    @Column(name = "FINISH_SALE", nullable = false)
    private LocalDate finishSale;

    @NotBlank(message = "Il campo non può essere vuoto")
    @Column(name = "DISCOUNT", nullable = false)
    private String discount;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartSale() {
        return startSale;
    }

    public void setStartSale(LocalDate startSale) {
        this.startSale = startSale;
    }

    public LocalDate getFinishSale() {
        return finishSale;
    }

    public void setFinishSale(LocalDate finishSale) {
        this.finishSale = finishSale;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
