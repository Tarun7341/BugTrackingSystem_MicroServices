package com.user.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.demo.dto.UserIdNameProjection;
import com.user.demo.entity.Roles;
import com.user.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	User findByRoles(Roles role);


	    @Query("SELECT u.id AS id, u.firstname AS firstname, u.lastname AS lastname FROM User u")
	    List<UserIdNameProjection> findIdAndNames();

}
