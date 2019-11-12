package edu.poly.Du_An_Tot_Ngiep.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.Du_An_Tot_Ngiep.Entity.Import;
import edu.poly.Du_An_Tot_Ngiep.Repository.ImportRepository;
import edu.poly.Du_An_Tot_Ngiep.Service.ImportService;


@Service
public class ImportSeviceImpl implements ImportService {

	@Autowired
	private ImportRepository importRepository;

	@Override
	public void deleteAll() {
		importRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Import> entities) {
		importRepository.deleteAll(entities);
		
	}

	@Override
	public void delete(Import entity) {
		importRepository.delete(entity);
		
	}

	@Override
	public void deleteById(Integer id) {
		importRepository.deleteById(id);
		
	}

	@Override
	public long count() {
		return importRepository.count();
	}

	@Override
	public Iterable<Import> findAllbyId(Iterable<Integer> ids) {
		return importRepository.findAllById(ids);
	}

	@Override
	public Iterable<Import> findAll() {
		return importRepository.findAll();
	}

	@Override
	public boolean existsById(Integer id) {
		return importRepository.existsById(id);
	}

	@Override
	public Optional<Import> findById(Integer id) {
		return importRepository.findById(id);
	}

	@Override
	public <S extends Import> Iterable<S> saveAll(Iterable<S> entities) {
		return importRepository.saveAll(entities);
	}

	@Override
	public <S extends Import> S save(S entity) {
		return importRepository.save(entity);
	}

	@Override
	public Optional<Import> findByName(String idUser) {
		return importRepository.findByName(idUser);
	}
	
}
