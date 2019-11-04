package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;

public interface UserService {

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

	<S extends User> List<S> findAll(Example<S> example);

	void deleteAll();

	<S extends User> boolean exists(Example<S> example);

	void deleteAll(Iterable<? extends User> entities);

	<S extends User> long count(Example<S> example);

	void delete(User entity);

	User getOne(Integer id);

	void deleteById(Integer id);

	void deleteAllInBatch();

	long count();

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<User> entities);

	<S extends User> S saveAndFlush(S entity);

	void flush();

	boolean existsById(Integer id);

	<S extends User> List<S> saveAll(Iterable<S> entities);

	List<User> findAllById(Iterable<Integer> ids);

	Optional<User> findById(Integer id);

	List<User> findAll(Sort sort);

	List<User> findAll();

	Page<User> findAll(Pageable pageable);

	<S extends User> Optional<S> findOne(Example<S> example);

	<S extends User> S save(S entity);

	
	
}
