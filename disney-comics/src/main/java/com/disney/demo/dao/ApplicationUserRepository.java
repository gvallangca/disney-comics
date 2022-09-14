package com.disney.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disney.demo.dao.entity.ApplicationUser;


public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String>{

	Optional<ApplicationUser> findByUsername(String username);
	
}
