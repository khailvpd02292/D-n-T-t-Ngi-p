package edu.poly.Du_An_Tot_Ngiep.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
