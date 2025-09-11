package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	 @Query("SELECT u FROM User u WHERE (u.mobile = :login OR u.email = :login) AND u.password = :password")
	    public User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

	 public List<User> findByRole(String role);


}

