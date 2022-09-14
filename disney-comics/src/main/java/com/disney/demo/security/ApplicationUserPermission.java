package com.disney.demo.security;
public enum ApplicationUserPermission {

	READ("read"), 
	WRITE("write");

	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}