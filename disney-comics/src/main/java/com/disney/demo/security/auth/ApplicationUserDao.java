package com.disney.demo.security.auth;
import java.util.Optional;
import com.disney.demo.dao.entity.ApplicationUser;

public interface ApplicationUserDao {

	Optional<ApplicationUser> selectApplicationUserByUserName(String username);
	
}