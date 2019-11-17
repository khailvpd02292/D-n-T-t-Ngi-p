package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.Du_An_Tot_Ngiep.Entity.Product;

public interface ProductService {

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	<S extends Product> List<S> findAll(Example<S> example);

	void deleteAll();

	<S extends Product> boolean exists(Example<S> example);

	void deleteAll(Iterable<? extends Product> entities);

	<S extends Product> long count(Example<S> example);

	void delete(Product entity);

	Product getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Product> entities);

	<S extends Product> S saveAndFlush(S entity);

	void flush();

	boolean existsById(Integer id);

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	List<Product> findAllById(Iterable<Integer> ids);

	Optional<Product> findById(Integer id);

	List<Product> findAll(Sort sort);

	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	<S extends Product> Optional<S> findOne(Example<S> example);

	<S extends Product> S save(S entity);

	List<Product> showListProductByIdCategory(int idCategory);

	List<Product> showProductDetaiList(int productId);

	List<Product> searchListProductByIdCategory(String name);

	List<Product> showListCategoryByIdCategory(int idCateogry);

	List<Product> showListProductForIndex();

	List<Product> listProduct();

	List<Product> findIdProduct(int idProduct);

	List<Product> listProductPriceAsc();

	List<Product> listProductPriceDesc();

	List<Product> listProductNewBest();

	List<Product> showListProductByIdCategoryFilter(int idCategory);

}
