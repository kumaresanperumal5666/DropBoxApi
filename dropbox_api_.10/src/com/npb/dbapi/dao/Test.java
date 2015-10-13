package com.npb.dbapi.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="test")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {
@Id
@GeneratedValue
long id;

@Column(name="f1")
String f1;

@Column(name="f2")
String f2;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getF1() {
	return f1;
}
public void setF1(String f1) {
	this.f1 = f1;
}
public String getF2() {
	return f2;
}
public void setF2(String f2) {
	this.f2 = f2;
}


}
