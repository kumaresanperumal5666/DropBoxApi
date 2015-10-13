package com.npb.dbapi.domain.core;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class DbFileUpload {
	
    @Id
    @GeneratedValue
	public long id;
	
    @Column(name="name")
	public String name;
    
	@Column(name="file")
	public Blob[] file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob[] getFile() {
		return file;
	}
	public void setFile(Blob[] file) {
		this.file = file;
	}
	
}
