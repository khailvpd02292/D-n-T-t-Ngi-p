package edu.poly.Du_An_Tot_Ngiep.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import edu.poly.Du_An_Tot_Ngiep.Entity.Category;
import edu.poly.Du_An_Tot_Ngiep.Repository.CategoryRepository;
import edu.poly.Du_An_Tot_Ngiep.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public List<Category> showCategoryByProduct(int idCategory) {
		return categoryRepository.showCategoryByProduct(idCategory);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public <S extends Category> long count(Example<S> example) {
		return categoryRepository.count(example);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example) {
		return categoryRepository.findAll(example);
	}

}
