package com.disney.demo.dao.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.disney.demo.security.ApplicationUserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_user")
public class ApplicationUser implements UserDetails {

	private static final long serialVersionUID = 6579540984750325203L;

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "authority")
	private String authority;

	@Transient
	private Set<? extends GrantedAuthority> grantedAuthorities;

	@Column(name = "is_account_non_expired")
	private boolean isAccountNonExpired;

	@Column(name = "is_account_non_locked")
	private boolean isAccountNonLocked;

	@Column(name = "is_credentials_non_expired")
	private boolean isCredentialsNonExpired;

	@Column(name = "is_enabled")
	private boolean isEnabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Arrays.stream(ApplicationUserRole.values()).filter(role -> role.name().equalsIgnoreCase(authority))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException(String.format("Unknown permission: %s", authority)))
				.getGrantedAuthorities();

	}

}
