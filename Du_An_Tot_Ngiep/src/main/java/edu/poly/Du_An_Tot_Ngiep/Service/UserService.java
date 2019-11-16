package edu.poly.Du_An_Tot_Ngiep.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;

public interface UserService {

	void deleteAll();

	void deleteAll(Iterable<? extends User> entities);

	void delete(User entity);

	void deleteById(Integer id);

	long count();

	Iterable<User> findAllById(Iterable<Integer> ids);

	Iterable<User> findAll();

	boolean existsById(Integer id);

	Optional<User> findById(Integer id);

	<S extends User> Iterable<S> saveAll(Iterable<S> entities);

	<S extends User> S save(S entity);

	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);

	

	
	
}
