package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.Du_An_Tot_Ngiep.Entity.Category;

public interface CategoryService {

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	<S extends Category> List<S> findAll(Example<S> example);

	void deleteAll();

	<S extends Category> boolean exists(Example<S> example);

	void deleteAll(Iterable<? extends Category> entities);

	<S extends Category> long count(Example<S> example);

	void delete(Category entity);

	Category getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	<S extends Category> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<Integer> ids);

	Optional<Category> findById(Integer id);

	List<Category> findAll(Sort sort);

	List<Category> findAll();

	<S extends Category> Optional<S> findOne(Example<S> example);

	<S extends Category> S save(S entity);

	List<Category> showCategoryByProduct(int idCategory);

}
