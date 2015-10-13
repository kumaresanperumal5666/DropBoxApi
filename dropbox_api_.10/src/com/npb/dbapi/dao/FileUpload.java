package com.npb.dbapi.dao;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.jdbc.Blob;


@Entity
@Table(name="file")
@JsonIgnoreProperties(ignoreUnknown = true)

@SqlResultSetMapping(name = "test",
classes = { @ConstructorResult(targetClass = FileUpload.class, 
columns = {
@ColumnResult(name = "id", type = Long.class),
@ColumnResult(name = "file", type = Blob.class),
@ColumnResult(name = "name", type = String.class)
}) 
})
public class FileUpload {

	@Id
	@GeneratedValue
	public long id;
	
	@Column(name="name")
	public String name;
    
	@Column(name = "file")
	public Blob[] file;
	


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
