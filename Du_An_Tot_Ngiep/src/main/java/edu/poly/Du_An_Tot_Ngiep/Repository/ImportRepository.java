package edu.poly.Du_An_Tot_Ngiep.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.Import;



@Repository
public interface ImportRepository extends JpaRepository<Import, Integer>{
	
	@Query(value = "select * from import where id_user  = ?", nativeQuery = true)
	Optional<Import> findByName(String IdUser);

	@Query(value = "select * from import where id_product = ?", nativeQuery = true)
	List<Import> showListImportByIdProduct(int idProduct);
	
	@Query(value = "select * from import where id_user = ?", nativeQuery = true)
	List<Import> showListImportByIdUser(String IdUser);
	
	@Query(value = "select * from import where  name like %?% ", nativeQuery = true)
	List<Import> searchListImportByIdUser(String IdUser);
	
	@Query(value = "select * from import where  name like %?% ", nativeQuery = true)
	List<Import> searchListImportByIdProduct(String idProduct);
}
