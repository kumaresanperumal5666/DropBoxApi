package com.npb.dbapi.domain.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="user_roles")

public class Roles {
	@Id
	@GeneratedValue
	private long user_role_id;
	private long user_id;
	private String authority;
	
	
	public long getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(long user_role_id) {
		this.user_role_id = user_role_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	


}
