package com.npb.dbapi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.npb.dbapi.domain.core.DbUserAppDetails;
import com.npb.dbapi.interfaces.dao.IDbUserAppDetailsDao;
/**
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 * 
 *         Purpose of this class is used to handle the CRUD operation methods
 *         for UserAppDetailsDao
 *         </p>
 *  
 * **/
@Repository
@Transactional
public class DbUserAppDetailsDao implements IDbUserAppDetailsDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Value("${createuserappdetails.sql}")
	private String createUserAppDetails;
	
	@Value("${searchuserappdetails.sql}")
	private String searchUserAppDetails;
	
	@Value("${updateuserappdetails.sql}")
	private String updateUserAppDetails;
	
	@Value("${deleteuserappdetails.sql}")
	private String deleteUserAppDetails;
	
	
	@Override
	@Transactional
	public DbUserAppDetails create(DbUserAppDetails userappdetails) {
		Query query= entityManager.createNativeQuery(this.createUserAppDetails)
			    //.setParameter("id",userappdetails.getId())
				.setParameter("username", userappdetails.getUsername())
				.setParameter("app_key", userappdetails.getApp_key())
				.setParameter("app_secret_key", userappdetails.getApp_secret_key())
				.setParameter("access_token", userappdetails.getAccess_token());
				query.executeUpdate();
		return userappdetails;
	}
	
	@Override
	@Transactional
	public DbUserAppDetails search(long id) {
		/*Query query = entityManager.createNativeQuery(this.searchUserAppDetails, "userappdetails")
				.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<UserAppDetails> userappdetails =(ArrayList<UserAppDetails>)query.getResultList();
		return userappdetails.get(0);*/
		
		System.out.println("we are search in search method and id is:  "+ id );
		Query query = entityManager.createNativeQuery(this.searchUserAppDetails)
				.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Object[]> userappdetails =(ArrayList<Object[]>)query.getResultList();
		DbUserAppDetails dto_list=new DbUserAppDetails();
		List<DbUserAppDetails> list_obj=new ArrayList<DbUserAppDetails>();
		for (Object[] objects : userappdetails) {
			dto_list.setId(Long.valueOf(objects[0].toString()));
			dto_list.setUsername(objects[1].toString());
			dto_list.setApp_key(objects[2].toString());
			dto_list.setApp_secret_key(objects[3].toString());
			dto_list.setAccess_token(objects[4].toString());
			list_obj.add(dto_list);
			}
		return list_obj.get(0);
	}

	@Override
	@Transactional
	public void delete(long id) {
		Query query=entityManager.createNativeQuery(this.deleteUserAppDetails)
				.setParameter("id", id);
		query.executeUpdate();
		
	}

	@Override
	@Transactional
	public DbUserAppDetails update(DbUserAppDetails userappdetails) {
		Query query= entityManager.createNativeQuery(this.updateUserAppDetails)
			    .setParameter("id",userappdetails.getId())
				.setParameter("username", userappdetails.getUsername())
				.setParameter("app_key", userappdetails.getApp_key())
				.setParameter("app_secret_key", userappdetails.getApp_secret_key())
				.setParameter("access_token", userappdetails.getAccess_token());
				query.executeUpdate();
		return userappdetails;
	}
}
