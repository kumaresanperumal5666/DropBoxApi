package com.npb.dbapi.domain.core;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 *         </p>
 *  
 * **/

@Entity
@Table(name = "user_app_details")
@JsonIgnoreProperties(ignoreUnknown = true)
/*@SqlResultSetMapping(name = "userappdetails",
        classes = { @ConstructorResult(targetClass = DbUserAppDetails.class, 
        columns = {
		@ColumnResult(name = "id", type = Long.class),
		@ColumnResult(name = "username", type = String.class),
		@ColumnResult(name = "app_key", type = String.class),
		@ColumnResult(name = "app_secret_key", type = String.class),
		@ColumnResult(name = "access_token", type = String.class) 
		}) 
        })*/
public class DbUserAppDetails {

/**public DbUserAppDetails(){
	
}
	public DbUserAppDetails(long id, String username, String app_key,
			String app_secret_key, String access_token) {
		this.id = id;
		this.username = username;
		this.app_key = app_key;
		this.app_secret_key = app_secret_key;
		this.access_token = access_token;
	}*/

	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String app_key;
	private String app_secret_key;
	private String access_token;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getApp_secret_key() {
		return app_secret_key;
	}

	public void setApp_secret_key(String app_secret_key) {
		this.app_secret_key = app_secret_key;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}