package edu.poly.Du_An_Tot_Ngiep.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	@Query(value = "SELECT * FROM users  WHERE email = ?", nativeQuery = true)
	Optional<User> findByName(String name);
}
