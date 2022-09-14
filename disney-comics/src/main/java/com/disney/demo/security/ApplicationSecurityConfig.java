package com.disney.demo.security;
import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.disney.demo.security.auth.ApplicationUserService;
import com.disney.demo.security.jwt.JwtConfig;
import com.disney.demo.security.jwt.JwtTokenVerifier;
import com.disney.demo.security.jwt.JwtUnamePwordAuthFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;

	/**
	 * This method secures the URL according to ROLES or Permissions(authorities)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		
		/* Enabling CSRF is recommended for browser users, else disabled */
		.csrf().disable()
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		
		/* This invokes the filter chain */
		.addFilter(new JwtUnamePwordAuthFilter(authenticationManager(), jwtConfig, secretKey))
		.addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUnamePwordAuthFilter.class)
		
		.authorizeRequests()
		
		/* This whitelist(exclude for authentication) some pattern-url to skip httpBasic() authentication */
		// .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
		
		/* This only allow users with role = STUDENT */
		// .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		
		.anyRequest()
		.authenticated();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		
		return provider;
		
	}

}