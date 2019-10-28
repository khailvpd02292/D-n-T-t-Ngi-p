package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.Du_An_Tot_Ngiep.Entity.ProductDetail;

public interface ProductDetailService {

	<S extends ProductDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductDetail> List<S> findAll(Example<S> example);

	void deleteAll();

	<S extends ProductDetail> boolean exists(Example<S> example);

	void deleteAll(Iterable<? extends ProductDetail> entities);

	<S extends ProductDetail> long count(Example<S> example);

	void delete(ProductDetail entity);

	ProductDetail getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	<S extends ProductDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ProductDetail> entities);

	<S extends ProductDetail> S saveAndFlush(S entity);

	void flush();

	boolean existsById(Integer id);

	<S extends ProductDetail> List<S> saveAll(Iterable<S> entities);

	List<ProductDetail> findAllById(Iterable<Integer> ids);

	Optional<ProductDetail> findById(Integer id);

	List<ProductDetail> findAll(Sort sort);

	List<ProductDetail> findAll();

	Page<ProductDetail> findAll(Pageable pageable);

	<S extends ProductDetail> Optional<S> findOne(Example<S> example);

	<S extends ProductDetail> S save(S entity);

}
