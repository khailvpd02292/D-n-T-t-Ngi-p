package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;

public interface CategoryService {

	<S extends Category> List<S> findAll(Example<S> example);

	<S extends Category> long count(Example<S> example);

	void delete(Category entity);

	void deleteById(Integer id);

	long count();

	Optional<Category> findById(Integer id);

	List<Category> findAll(Sort sort);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> showCategoryByProduct(int idCategory);

}
