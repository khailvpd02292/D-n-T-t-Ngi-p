package edu.poly.Du_An_Tot_Ngiep.Repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.poly.Du_An_Tot_Ngiep.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM users  WHERE email = ?", nativeQuery = true)
	Optional<User> findByEmail(String email);
	@Query(value = "SELECT * FROM users  WHERE fullname = ?", nativeQuery = true)
	Optional<User> findByName(String name);

}
