package com.npb.dbapi.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.npb.dbapi.interfaces.dao.IDbAuthDao;

@Repository
@Transactional
public class DbAuthDao implements IDbAuthDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Value("${test.sql}")
	private String test;

	@Value("${download.sql}")
	private String download; 
	
	@Override
	@Transactional
	public void datastore(MultipartFile file,String userString) throws IOException {
		System.out.println("dao method called @@@");
		Blob[] b = new Blob[file.getInputStream().available()];
		//Byte[] c =new Byte[file.getInputStream().available()];
	    Query query= entityManager.createNativeQuery(this.test)
 				.setParameter("file", b)
				.setParameter("name", userString);
				query.executeUpdate();
		
		
	}

	public FileUpload download() {
		System.out.println("we are search in search method and id is:  ");
		Query query = entityManager.createNativeQuery(this.download)
				.setParameter("id", 1);
		@SuppressWarnings("unchecked")
		List<String[]> userappdetails =(ArrayList<String[]>)query.getResultList();
		FileUpload dto_list=new FileUpload();
		List<FileUpload> list_obj=new ArrayList<FileUpload>();
		for (String[] objects : userappdetails) {
			dto_list.setId(Long.valueOf(objects[0].toString()));
			File f=new File(objects[1]);
	
			//dto_list.setFile(f);
			dto_list.setName("");
			list_obj.add(dto_list);
			}
		return list_obj.get(0);
		
	}
}
