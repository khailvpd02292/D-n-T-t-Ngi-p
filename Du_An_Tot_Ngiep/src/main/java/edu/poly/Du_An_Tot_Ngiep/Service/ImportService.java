package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.Optional;

import edu.poly.Du_An_Tot_Ngiep.Entity.Import;

public interface ImportService {

	void deleteAll();
	
	void deleteAll(Iterable<? extends Import> entities);
	
	void delete(Import entity);
	
	void deleteById(Integer id);
	
	long count();
	
	Iterable<Import> findAllbyId(Iterable<Integer> ids);
	
	Iterable<Import> findAll();
	
	boolean existsById(Integer id);
	
	Optional<Import> findById(Integer id);
	
	<S extends Import> Iterable<S> saveAll(Iterable<S> entities);
	
	<S extends Import> S save (S entity);
	
	
	Optional<Import> findByName(String idUser);
	
}
