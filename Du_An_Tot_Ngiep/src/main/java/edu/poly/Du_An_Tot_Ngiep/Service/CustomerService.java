package edu.poly.Du_An_Tot_Ngiep.Service;

import java.util.Optional;

import edu.poly.Du_An_Tot_Ngiep.Entity.Customer;

public interface CustomerService {

	void deleteAll();

	void deleteAll(Iterable<? extends Customer> entities);

	void delete(Customer entity);

	void deleteById(Integer id);

	long count();

	Iterable<Customer> findAllById(Iterable<Integer> ids);

	Iterable<Customer> findAll();

	boolean existsById(Integer id);

	Optional<Customer> findById(Integer id);

	<S extends Customer> Iterable<S> saveAll(Iterable<S> entities);

	<S extends Customer> S save(S entity);

	Optional<Customer> findByPhoneCus(String phone);

}
