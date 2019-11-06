package edu.poly.Du_An_Tot_Ngiep.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query(value = "select * from category where idCategory = ? ", nativeQuery = true)
	List<Category> showCategoryByProduct(int idCategory);
}
