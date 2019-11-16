package edu.poly.Du_An_Tot_Ngiep.ServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;
import edu.poly.Du_An_Tot_Ngiep.Repository.UserRepository;
import edu.poly.Du_An_Tot_Ngiep.Service.UserService;
@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
		return userRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> findAllById(Iterable<Integer> ids) {
		return userRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends User> entities) {
		userRepository.deleteAll(entities);
	}


	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public Optional<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	
}
