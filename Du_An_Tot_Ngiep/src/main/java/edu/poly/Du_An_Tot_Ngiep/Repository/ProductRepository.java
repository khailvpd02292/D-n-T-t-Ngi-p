package edu.poly.Du_An_Tot_Ngiep.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
	@Query(value = "select * from product where id_category = ?", nativeQuery = true)
	List<Product> showListProductByIdCategory(int idCategory);
	
	@Query(value = "select * from product where id_product = ?", nativeQuery = true )
	List<Product> showProductDetaiList(int productId);
	
	@Query(value = "select * from product where  name like %?% ", nativeQuery = true)
	List<Product> searchListProductByIdCategory(String name);
}
