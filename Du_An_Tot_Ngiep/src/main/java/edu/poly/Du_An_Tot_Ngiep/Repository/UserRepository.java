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
	Optional<User> findByName(String name);

	@Modifying
	@Query(value = "insert into Users (address,birthday,email,fullname,gender,password,phone) values (:address, :birthday, :email, :fullname, :gender, :password, :phone)", nativeQuery = true)
	void insertUser(@Param("address") String address, @Param("birthday") Date birthday, @Param("email") String email,
			@Param("fullname") String fullname, @Param("gender") boolean gender, @Param("password") String password,
			@Param("phone") String phone);
}
