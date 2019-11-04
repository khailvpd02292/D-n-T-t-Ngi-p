package edu.poly.Du_An_Tot_Ngiep.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.Du_An_Tot_Ngiep.Entity.ProductDetail;
import edu.poly.Du_An_Tot_Ngiep.Repository.ProductDetailRepository;
import edu.poly.Du_An_Tot_Ngiep.Service.ProductDetailService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	
	@Autowired
	private ProductDetailRepository productDetailRepository;

	@Override
	public <S extends ProductDetail> S save(S entity) {
		return productDetailRepository.save(entity);
	}

	@Override
	public <S extends ProductDetail> Optional<S> findOne(Example<S> example) {
		return productDetailRepository.findOne(example);
	}

	@Override
	public Page<ProductDetail> findAll(Pageable pageable) {
		return productDetailRepository.findAll(pageable);
	}

	@Override
	public List<ProductDetail> findAll() {
		return productDetailRepository.findAll();
	}

	@Override
	public List<ProductDetail> findAll(Sort sort) {
		return productDetailRepository.findAll(sort);
	}

	@Override
	public Optional<ProductDetail> findById(Integer id) {
		return productDetailRepository.findById(id);
	}

	@Override
	public List<ProductDetail> findAllById(Iterable<Integer> ids) {
		return productDetailRepository.findAllById(ids);
	}

	@Override
	public <S extends ProductDetail> List<S> saveAll(Iterable<S> entities) {
		return productDetailRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return productDetailRepository.existsById(id);
	}

	@Override
	public void flush() {
		productDetailRepository.flush();
	}

	@Override
	public <S extends ProductDetail> S saveAndFlush(S entity) {
		return productDetailRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<ProductDetail> entities) {
		productDetailRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDetailRepository.findAll(example, pageable);
	}

	@Override
	public long count() {
		return productDetailRepository.count();
	}

	@Override
	public void deleteAllInBatch() {
		productDetailRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		productDetailRepository.deleteById(id);
	}

	@Override
	public ProductDetail getOne(Integer id) {
		return productDetailRepository.getOne(id);
	}

	@Override
	public void delete(ProductDetail entity) {
		productDetailRepository.delete(entity);
	}

	@Override
	public <S extends ProductDetail> long count(Example<S> example) {
		return productDetailRepository.count(example);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductDetail> entities) {
		productDetailRepository.deleteAll(entities);
	}

	@Override
	public <S extends ProductDetail> boolean exists(Example<S> example) {
		return productDetailRepository.exists(example);
	}

	@Override
	public void deleteAll() {
		productDetailRepository.deleteAll();
	}

	@Override
	public <S extends ProductDetail> List<S> findAll(Example<S> example) {
		return productDetailRepository.findAll(example);
	}

	@Override
	public <S extends ProductDetail> List<S> findAll(Example<S> example, Sort sort) {
		return productDetailRepository.findAll(example, sort);
	}
	

}
