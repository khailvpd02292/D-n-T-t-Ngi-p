package edu.poly.Du_An_Tot_Ngiep.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.Du_An_Tot_Ngiep.Entity.Product;
import edu.poly.Du_An_Tot_Ngiep.Repository.ProductRepository;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public <S extends Product> S save(S entity) {
		return productRepository.save(entity);
	}

	@Override
	public List<Product> listProductNewBest() {
		return productRepository.listProductNewBest();
	}

	@Override
	public List<Product> showListProductByIdCategoryFilter(int idCategory) {
		return productRepository.showListProductByIdCategoryFilter(idCategory);
	}

	@Override
	public List<Product> listProductPriceDesc() {
		return productRepository.listProductPriceDesc();
	}

	@Override
	public List<Product> listProductPriceAsc() {
		return productRepository.listProductPriceAsc();
	}

	@Override
	public List<Product> findIdProduct(int idProduct) {
		return productRepository.findIdProduct(idProduct);
	}

	@Override
	public List<Product> listProduct() {
		return productRepository.listProduct();
	}

	@Override
	public List<Product> showListCategoryByIdCategory(int idCateogry) {
		return productRepository.showListCategoryByIdCategory(idCateogry);
	}

	@Override
	public List<Product> showListProductForIndex() {
		return productRepository.showListProductForIndex();
	}

	@Override
	public List<Product> showProductDetaiList(int productId) {
		return productRepository.showProductDetaiList(productId);
	}

	@Override
	public List<Product> searchListProductByIdCategory(String name) {
		return productRepository.searchListProductByIdCategory(name);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productRepository.findOne(example);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> showListProductByIdCategory(int idCategory) {
		return productRepository.showListProductByIdCategory(idCategory);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findAllById(Iterable<Integer> ids) {
		return productRepository.findAllById(ids);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return productRepository.existsById(id);
	}

	@Override
	public void flush() {
		productRepository.flush();
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return productRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		productRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productRepository.findAll(example, pageable);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		productRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product getOne(Integer id) {
		return productRepository.getOne(id);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return productRepository.count(example);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		productRepository.deleteAll(entities);
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return productRepository.exists(example);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return productRepository.findAll(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productRepository.findAll(example, sort);
	}
	
	
}
