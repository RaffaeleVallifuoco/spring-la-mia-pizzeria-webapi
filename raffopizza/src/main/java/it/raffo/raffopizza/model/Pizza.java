package it.raffo.raffopizza.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull(message = "Campo Obblihatorio")
    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "Campo Obblihatorio")
    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "photo")
    private String photo;

    @NotNull(message = "Campo Obblihatorio")
    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "pizza")
    private List<Sale> sale;

    // --------------------------------------------
    // ------------ GETTERS & SETTERS -------------
    // --------------------------------------------

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Sale> getSale() {
        return sale;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }

}
