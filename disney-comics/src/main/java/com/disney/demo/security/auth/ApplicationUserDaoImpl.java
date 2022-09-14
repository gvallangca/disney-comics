package com.disney.demo.security.auth;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.disney.demo.dao.ApplicationUserRepository;
import com.disney.demo.dao.entity.ApplicationUser;

import lombok.AllArgsConstructor;

@Repository("dbuser")
@AllArgsConstructor
public class ApplicationUserDaoImpl implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	private final ApplicationUserRepository applicationUserRepository;
	
	@Override
	public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
		
		Optional<ApplicationUser> dbUser = applicationUserRepository.findByUsername(username);
		return dbUser;
	}

}
