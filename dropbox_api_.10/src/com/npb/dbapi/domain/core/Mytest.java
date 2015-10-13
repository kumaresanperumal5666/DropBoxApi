package com.npb.dbapi.domain.core;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class Mytest {

	@Id
	@GeneratedValue
	long id;
	@Column(name="name")
	String name;
	@Column(name="file")
	Blob[] file;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
