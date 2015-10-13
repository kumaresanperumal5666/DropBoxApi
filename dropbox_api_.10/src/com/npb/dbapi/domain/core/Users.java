package com.npb.dbapi.domain.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 
 * 
 * */
@Entity
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue
	private long user_id;
	private String username;
	private String password;
	private long active;
	
	@OneToOne
	private Roles roles;
	
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}
	
}
