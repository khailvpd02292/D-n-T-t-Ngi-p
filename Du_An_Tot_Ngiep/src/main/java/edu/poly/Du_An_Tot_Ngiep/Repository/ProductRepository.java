package edu.poly.Du_An_Tot_Ngiep.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
