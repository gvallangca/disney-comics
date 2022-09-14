package com.disney.demo.security;
import static com.disney.demo.security.ApplicationUserPermission.READ;
import static com.disney.demo.security.ApplicationUserPermission.WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {

	// ADMIN has all the permissions
	ADMIN(Sets.newHashSet(READ, WRITE));

	private final Set<ApplicationUserPermission> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

	/**
	 * Define the set of permissions of the role
	 * @return
	 */
	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

		Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	
		return permissions;
	}

}
