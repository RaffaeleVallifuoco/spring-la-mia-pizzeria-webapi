package it.raffo.raffopizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.raffo.raffopizza.model.Sale;

public interface SaleRepo extends JpaRepository<Sale, Integer> {

}
